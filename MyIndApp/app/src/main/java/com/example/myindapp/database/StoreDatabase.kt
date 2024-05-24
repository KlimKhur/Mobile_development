package com.example.myindapp.database

@Database(entities = [Category::class, Store::class], version = 1)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun storeDao(): StoreDao

    companion object {
        @Volatile
        private var INSTANCE: StoreDatabase? = null

        fun getDatabase(context: Context): StoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoreDatabase::class.java,
                    "store_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}