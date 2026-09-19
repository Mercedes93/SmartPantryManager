package com.smartpantry.manager.database;

public class DatabaseSeeder {
    public static void seedRecipes(RecipeDao dao){
        if (dao.getRecipeCount()> 0) return;

        public static void seedRecipes(RecipeDao dao) {
            if (dao.getRecipeCount() > 0) return;

            //1. Braai Boerewors
            long r1 = dao.insertRecipe(new Recipe(
                    "Braai Boerewors",
                    "1. Light the braai and wait for medium-hot coals.\n" +
                            "2. Coil the boerewors on the grid — do not prick it.\n" +
                            "3. Braai 8–10 min per side until cooked through.\n" +
                            "4. Serve with pap or bread rolls.",
                    4));
            dao.insertIngredient(new RecipeIngredient((int)r1, "boerewors", 500, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r1, "vegetable oil", 10, "ml"));

            // 2. Pap and Chakalaka
            long r2 = dao.insertRecipe(new Recipe(
                    "Pap and Chakalaka",
                    "1. Bring salted water to a boil. Slowly pour in maize meal, stirring.\n" +
                            "2. Reduce heat, cover and cook 20 min, stirring occasionally.\n" +
                            "3. Fry onion and garlic in oil until soft.\n" +
                            "4. Add bell pepper, tomato, baked beans, curry powder and salt.\n" +
                            "5. Simmer chakalaka 15 min. Serve over pap.",
                    4));
            dao.insertIngredient(new RecipeIngredient((int)r2, "maize meal", 300, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r2, "onion", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r2, "garlic", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r2, "bell pepper", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r2, "tomato", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r2, "baked beans", 200, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r2, "curry powder", 5, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r2, "vegetable oil", 20, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r2, "salt", 3, "g"));

            // 3. Bobotie
            long r3 = dao.insertRecipe(new Recipe(
                    "Bobotie",
                    "1. Preheat oven to 180°C.\n" +
                            "2. Fry onion and garlic in butter until soft.\n" +
                            "3. Add mince, curry powder, turmeric, apricot jam and salt. Cook 10 min.\n" +
                            "4. Soak bread in milk, squeeze out and mix into mince.\n" +
                            "5. Spoon into a greased baking dish.\n" +
                            "6. Whisk eggs with remaining milk, pour over top.\n" +
                            "7. Bake 35–40 min until custard is set and golden.",
                    4));
            dao.insertIngredient(new RecipeIngredient((int)r3, "beef mince", 500, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r3, "onion", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r3, "garlic", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r3, "curry powder", 10, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r3, "turmeric", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r3, "apricot jam", 30, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r3, "bread", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r3, "milk", 200, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r3, "egg", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r3, "butter", 20, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r3, "salt", 3, "g"));

            // 4. Malva Pudding
            long r4 = dao.insertRecipe(new Recipe(
                    "Malva Pudding",
                    "1. Preheat oven to 180°C.\n" +
                            "2. Cream butter and sugar. Beat in egg and apricot jam.\n" +
                            "3. Sift flour, bicarbonate of soda and salt. Add to mixture alternating with milk.\n" +
                            "4. Pour into greased baking dish. Bake 30–35 min.\n" +
                            "5. Mix cream, butter, sugar and vanilla for sauce; heat until dissolved.\n" +
                            "6. Pour hot sauce over pudding straight from oven. Serve warm.",
                    6));
            dao.insertIngredient(new RecipeIngredient((int)r4, "flour", 150, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r4, "sugar", 150, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r4, "egg", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r4, "butter", 60, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r4, "apricot jam", 30, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r4, "milk", 125, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r4, "bicarbonate of soda", 5, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r4, "cream", 200, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r4, "vanilla essence", 5, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r4, "salt", 1, "g"));

            //5. Milk Tart
            long r5 = dao.insertRecipe(new Recipe(
                    "Milk Tart",
                    "1. Mix crushed biscuits with melted butter, press into tart dish. Refrigerate.\n" +
                            "2. Heat milk in a saucepan until just simmering.\n" +
                            "3. Whisk eggs, sugar, flour and cornflour together.\n" +
                            "4. Slowly pour hot milk into egg mixture, whisking constantly.\n" +
                            "5. Return to heat, stir until thickened.\n" +
                            "6. Add vanilla, pour into crust. Refrigerate 2 hours.\n" +
                            "7. Dust generously with cinnamon before serving.",
                    8));
            dao.insertIngredient(new RecipeIngredient((int)r5, "milk", 750, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r5, "egg", 3, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r5, "sugar", 100, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r5, "flour", 40, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r5, "cornflour", 30, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r5, "butter", 30, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r5, "vanilla essence", 5, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r5, "cinnamon", 5, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r5, "tennis biscuits", 200, "g"));

            // 6. Vetek with Mince
            long r6 = dao.insertRecipe(new Recipe(
                    "Vetkoek with Mince",
                    "1. Mix flour, yeast, sugar and salt. Add warm water and knead 10 min.\n" +
                            "2. Cover and let rise 1 hour.\n" +
                            "3. Shape into balls and deep fry in hot oil until golden, turning once.\n" +
                            "4. Fry onion and garlic, add mince and cook through.\n" +
                            "5. Add tomato, curry powder and salt. Simmer 10 min.\n" +
                            "6. Slice vetkoek open and fill with mince.",
                    6));
            dao.insertIngredient(new RecipeIngredient((int)r6, "flour", 500, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r6, "yeast", 10, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r6, "sugar", 10, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r6, "salt", 5, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r6, "beef mince", 400, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r6, "onion", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r6, "garlic", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r6, "tomato", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r6, "curry powder", 5, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r6, "vegetable oil", 500, "ml"));

            //7. Boeber
            long r7 = dao.insertRecipe(new Recipe(
                    "Boeber",
                    "1. Melt butter in a large pot over medium heat.\n" +
                            "2. Add vermicelli and fry until lightly golden.\n" +
                            "3. Add milk, sugar, cardamom and cinnamon stick. Stir well.\n" +
                            "4. Simmer 20 min, stirring frequently.\n" +
                            "5. Add sago (pre-soaked) and rose water. Cook 10 more min.\n" +
                            "6. Serve warm in bowls.",
                    4));
            dao.insertIngredient(new RecipeIngredient((int)r7, "milk", 1000, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r7, "vermicelli", 100, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r7, "sugar", 80, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r7, "butter", 30, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r7, "cardamom", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r7, "cinnamon", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r7, "sago", 50, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r7, "rose water", 10, "ml"));

            //8. Koeksisters
            long r8 = dao.insertRecipe(new Recipe(
                    "Koeksisters",
                    "1. Make syrup: boil water, sugar, cream of tartar and ginger 5 min. Chill.\n" +
                            "2. Mix flour, baking powder and salt. Rub in butter.\n" +
                            "3. Add milk to form a soft dough. Rest 30 min.\n" +
                            "4. Roll out, cut into strips, plait or twist.\n" +
                            "5. Deep fry until golden. Immediately dip into cold syrup for 30 seconds.\n" +
                            "6. Drain on a rack.",
                    12));
            dao.insertIngredient(new RecipeIngredient((int)r8, "flour", 500, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r8, "baking powder", 10, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r8, "butter", 60, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r8, "milk", 200, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r8, "sugar", 500, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r8, "vegetable oil", 500, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r8, "salt", 2, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r8, "ginger", 3, "g"));

            //9. Bunny Chow
            long r9 = dao.insertRecipe(new Recipe(
                    "Bunny Chow",
                    "1. Fry onion, garlic and ginger in oil until golden.\n" +
                            "2. Add curry powder, turmeric and cumin. Stir 1 min.\n" +
                            "3. Add chicken pieces and brown on all sides.\n" +
                            "4. Add potato, tomato and salt. Cover with water.\n" +
                            "5. Simmer 35–40 min until chicken is cooked and potato is soft.\n" +
                            "6. Hollow out a quarter loaf of bread and fill with curry.",
                    4));
            dao.insertIngredient(new RecipeIngredient((int)r9, "chicken", 600, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "onion", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "garlic", 3, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "ginger", 5, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "curry powder", 15, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "turmeric", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "cumin", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "potato", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "tomato", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "bread", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "vegetable oil", 30, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r9, "salt", 3, "g"));

            //10. Samp and Beans
            long r10 = dao.insertRecipe(new Recipe(
                    "Samp and Beans",
                    "1. Soak samp and beans overnight in cold water.\n" +
                            "2. Drain and cover with fresh water in a large pot.\n" +
                            "3. Boil 1.5–2 hours until samp is soft, topping up water as needed.\n" +
                            "4. Fry onion and garlic in butter until golden.\n" +
                            "5. Stir into samp with salt and pepper.\n" +
                            "6. Serve as a side or main.",
                    6));
            dao.insertIngredient(new RecipeIngredient((int)r10, "samp", 300, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r10, "beans", 150, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r10, "onion", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r10, "garlic", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r10, "butter", 30, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r10, "salt", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r10, "black pepper", 2, "g"));

            //11. Melktert Smoothie
            long r11 = dao.insertRecipe(new Recipe(
                    "Melktert Smoothie",
                    "1. Add all ingredients to a blender.\n" +
                            "2. Blend on high until smooth and creamy.\n" +
                            "3. Pour into glasses and dust with cinnamon.",
                    2));
            dao.insertIngredient(new RecipeIngredient((int)r11, "milk", 300, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r11, "vanilla essence", 5, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r11, "cinnamon", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r11, "sugar", 20, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r11, "egg", 1, "pcs"));

            //12. Roosterkoek
            long r12 = dao.insertRecipe(new Recipe(
                    "Roosterkoek",
                    "1. Mix flour, yeast, sugar and salt.\n" +
                            "2. Add warm water and knead into a soft dough. Rest 1 hour.\n" +
                            "3. Shape into round rolls.\n" +
                            "4. Place on braai grid over medium coals.\n" +
                            "5. Cook 10–12 min per side until hollow when tapped.\n" +
                            "6. Serve with butter and jam.",
                    8));
            dao.insertIngredient(new RecipeIngredient((int)r12, "flour", 500, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r12, "yeast", 10, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r12, "sugar", 15, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r12, "salt", 5, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r12, "butter", 20, "g"));

            //13. Chicken Potjie
            long r13 = dao.insertRecipe(new Recipe(
                    "Chicken Potjie",
                    "1. Brown chicken pieces in oil in the potjie pot.\n" +
                            "2. Add onion and garlic; cook 3 min.\n" +
                            "3. Layer potato, carrot and bell pepper on top — do not stir.\n" +
                            "4. Mix tomato, stock, salt and pepper; pour over.\n" +
                            "5. Cover and cook on low coals 1.5 hours without lifting the lid.\n" +
                            "6. Stir gently before serving.",
                    5));
            dao.insertIngredient(new RecipeIngredient((int)r13, "chicken", 800, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r13, "onion", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r13, "garlic", 3, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r13, "potato", 3, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r13, "carrot", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r13, "bell pepper", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r13, "tomato", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r13, "vegetable oil", 30, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r13, "salt", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r13, "black pepper", 2, "g"));

            // 14. Peri-Peri Chicken
            long r14 = dao.insertRecipe(new Recipe(
                    "Peri-Peri Chicken",
                    "1. Mix peri-peri sauce, lemon juice, garlic, oil and salt into a marinade.\n" +
                            "2. Score chicken pieces and coat in marinade. Refrigerate 2 hours minimum.\n" +
                            "3. Braai or grill over medium heat 35–40 min, turning and basting often.\n" +
                            "4. Rest 5 min before serving.",
                    4));
            dao.insertIngredient(new RecipeIngredient((int)r14, "chicken", 800, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r14, "peri-peri sauce", 80, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r14, "lemon juice", 30, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r14, "garlic", 3, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r14, "vegetable oil", 30, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r14, "salt", 3, "g"));

            //15. Amarula Custard
            long r15 = dao.insertRecipe(new Recipe(
                    "Amarula Custard",
                    "1. Whisk egg yolks and sugar until pale.\n" +
                            "2. Heat cream and milk together until just simmering.\n" +
                            "3. Slowly pour hot cream into yolks, whisking constantly.\n" +
                            "4. Return to low heat, stir until thickened enough to coat a spoon.\n" +
                            "5. Remove from heat and stir in Amarula.\n" +
                            "6. Serve warm over malva pudding or milk tart.",
                    4));
            dao.insertIngredient(new RecipeIngredient((int)r15, "egg", 4, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r15, "sugar", 60, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r15, "cream", 250, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r15, "milk", 125, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r15, "amarula", 60, "ml"));

            //16. Tomato Bredie
            long r16 = dao.insertRecipe(new Recipe(
                    "Tomato Bredie",
                    "1. Brown lamb pieces in oil in a heavy pot.\n" +
                            "2. Add onion and garlic; cook until soft.\n" +
                            "3. Add tomato, sugar, salt, pepper and a little water.\n" +
                            "4. Cover and simmer on low heat 1.5–2 hours until lamb is very tender.\n" +
                            "5. Adjust seasoning and serve with white rice.",
                    4));
            dao.insertIngredient(new RecipeIngredient((int)r16, "lamb", 600, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r16, "tomato", 5, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r16, "onion", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r16, "garlic", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r16, "sugar", 10, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r16, "vegetable oil", 30, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r16, "salt", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r16, "black pepper", 2, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r16, "rice", 200, "g"));

            //17. Boboties Eggless (Lentil Bobotie)
            long r17 = dao.insertRecipe(new Recipe(
                    "Lentil Bobotie",
                    "1. Preheat oven to 180°C.\n" +
                            "2. Fry onion and garlic in oil until soft.\n" +
                            "3. Add lentils, curry powder, turmeric, apricot jam and salt. Cook 5 min.\n" +
                            "4. Spoon into a greased baking dish.\n" +
                            "5. Whisk eggs with milk, pour over top.\n" +
                            "6. Bake 30 min until topping is set and golden.",
                    4));
            dao.insertIngredient(new RecipeIngredient((int)r17, "lentils", 300, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r17, "onion", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r17, "garlic", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r17, "curry powder", 10, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r17, "turmeric", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r17, "apricot jam", 30, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r17, "egg", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r17, "milk", 150, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r17, "vegetable oil", 20, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r17, "salt", 3, "g"));

            //18. Umngqusho (Xhosa Samp and Beans)
            long r18 = dao.insertRecipe(new Recipe(
                    "Umngqusho",
                    "1. Soak samp and cowpeas overnight.\n" +
                            "2. Drain and boil in fresh salted water 2–3 hours until very soft.\n" +
                            "3. Fry onion in butter until caramelised.\n" +
                            "4. Stir onion, salt and pepper into the samp.\n" +
                            "5. Serve hot as a main or side dish.",
                    6));
            dao.insertIngredient(new RecipeIngredient((int)r18, "samp", 250, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r18, "cowpeas", 150, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r18, "onion", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r18, "butter", 30, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r18, "salt", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r18, "black pepper", 2, "g"));

            //19. Samoosas
            long r19 = dao.insertRecipe(new Recipe(
                    "Samoosas",
                    "1. Fry onion and garlic in oil until golden.\n" +
                            "2. Add mince, curry powder, cumin, turmeric and salt. Cook until dry.\n" +
                            "3. Stir in fresh coriander. Cool completely.\n" +
                            "4. Cut spring roll pastry into strips. Place a spoon of filling at one end.\n" +
                            "5. Fold into triangles, sealing with a flour-water paste.\n" +
                            "6. Deep fry in hot oil until golden and crispy.",
                    10));
            dao.insertIngredient(new RecipeIngredient((int)r19, "beef mince", 400, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r19, "onion", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r19, "garlic", 2, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r19, "curry powder", 10, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r19, "cumin", 3, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r19, "turmeric", 2, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r19, "coriander", 10, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r19, "spring roll pastry", 200, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r19, "vegetable oil", 500, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r19, "flour", 20, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r19, "salt", 3, "g"));

            //20. Rooibos Rusks
            long r20 = dao.insertRecipe(new Recipe(
                    "Rooibos Rusks",
                    "1. Preheat oven to 180°C.\n" +
                            "2. Brew strong rooibos tea and cool slightly.\n" +
                            "3. Mix flour, sugar, baking powder and salt.\n" +
                            "4. Rub in butter until crumbly.\n" +
                            "5. Add egg and enough rooibos to form a stiff dough.\n" +
                            "6. Shape into a loaf tin. Bake 45 min until golden.\n" +
                            "7. Cool, slice into fingers, dry in oven at 70°C overnight.",
                    16));
            dao.insertIngredient(new RecipeIngredient((int)r20, "flour", 500, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r20, "sugar", 100, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r20, "baking powder", 10, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r20, "butter", 125, "g"));
            dao.insertIngredient(new RecipeIngredient((int)r20, "egg", 1, "pcs"));
            dao.insertIngredient(new RecipeIngredient((int)r20, "rooibos tea", 200, "ml"));
            dao.insertIngredient(new RecipeIngredient((int)r20, "salt", 2, "g"));
        }
    }

