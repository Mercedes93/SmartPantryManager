Smart Pantry Manager

An Android application that helps users reduce food waste by tracking pantry ingredients and suggesting recipes they can cook using strictly what they already have no shopping required.

Database Choice: SQLite via Room

Room was chosen because:
- It runs entirely on-device with no internet dependency, making the app fully functional offline.
- Room integrates natively with Android's LiveData, so the UI automatically updates whenever the pantry changes.
- Setup requires only a `@Database` annotation  no server configuration.

 Project Structure

app/src/main/
├── java/com/smartpantry/manager/
│   ├── activities/
│   │   ├── MainActivity.java              # Bottom-nav host
│   │   ├── AddEditIngredientActivity.java # Add / edit pantry item
│   │   └── RecipeDetailActivity.java      # Full recipe view
│   ├── fragments/
│   │   ├── PantryListFragment.java        # Pantry RecyclerView
│   │   ├── SuggestedRecipesFragment.java  # Strict-matched recipes
│   │   └── SettingsFragment.java          # User preferences
│   ├── adapters/
│   │   ├── PantryAdapter.java
│   │   └── RecipeAdapter.java
│   ├── database/
│   │   ├── PantryItem.java                # Room entity
│   │   ├── Recipe.java                    # Room entity
│   │   ├── RecipeIngredient.java          # Room entity
│   │   ├── PantryDao.java
│   │   ├── RecipeDao.java
│   │   ├── AppDatabase.java               # Singleton Room DB
│   │   └── DatabaseSeeder.java            # 20 pre-loaded recipes
│   └── utils/
│       └── IngredientMatcher.java         # Strict-matching algorithm
└── res/
    ├── layout/          # All XML layouts
    ├── menu/            # Bottom navigation menu
    ├── values/          # colors, strings, themes
    └── drawable/        # Shape drawables

How to Run

1. Clone this repository.
2. Open the project in **Android Studio Hedgehog (2023.1.1)** or later.
3. Let Gradle sync and download dependencies.
4. Run on an emulator (API 26+) or a physical device.
5. On first launch the app automatically seeds 20 recipes into the local database.

Key Feature: Strict Matching

A recipe appears in **Suggested Recipes** only when **every** ingredient it needs is present in the pantry in at least the required quantity. Remove even one ingredient and the recipe disappears immediately. A separate **"Almost There"** section shows recipes missing exactly one ingredient.
 
