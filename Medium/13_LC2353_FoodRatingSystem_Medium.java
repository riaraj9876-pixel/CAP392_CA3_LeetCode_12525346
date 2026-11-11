import java.util.*;

class FoodRatings {

    Map<String, String> foodCuisine = new HashMap<>();
    Map<String, Integer> foodRating = new HashMap<>();
    Map<String, PriorityQueue<String>> cuisineFoods = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {

        for (int i = 0; i < foods.length; i++) {

            foodCuisine.put(foods[i], cuisines[i]);
            foodRating.put(foods[i], ratings[i]);

            cuisineFoods.putIfAbsent(cuisines[i],
                new PriorityQueue<>((a, b) -> {

                    int ratingCompare =
                        Integer.compare(foodRating.get(b), foodRating.get(a));

                    if (ratingCompare != 0) {
                        return ratingCompare;
                    }

                    return a.compareTo(b);
                }));

            cuisineFoods.get(cuisines[i]).offer(foods[i]);
        }
    }

    public void changeRating(String food, int newRating) {

        String cuisine = foodCuisine.get(food);

        foodRating.put(food, newRating);

        PriorityQueue<String> pq = cuisineFoods.get(cuisine);

        pq.remove(food);
        pq.offer(food);
    }

    public String highestRated(String cuisine) {

        return cuisineFoods.get(cuisine).peek();
    }
}
