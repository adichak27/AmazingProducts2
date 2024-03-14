package com.cs4520.assignment4
import android.content.Context
import android.util.Log
import androidx.room.Dao
import androidx.room.Room

object DatabaseClient {
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (instance == null) {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "database-name"
                    ).fallbackToDestructiveMigration().build()
                }
            }
        }
        return instance!!
    }
}
class ProductRepository(context: Context) {
    private val api = RetrofitClient.instance
    private val productDao = DatabaseClient.getDatabase(context).productDao()

    suspend fun getProducts(page: Int? = null): List<Product> {
        try {
            val response = api.getProducts(page)
            if (response.isSuccessful) { //&& !response.body().isNullOrEmpty()
                Log.d("MyTag", "ResponseLength: " + response.body()?.size)
                val products = response.body()!!
                productDao.insertAll(products)
                Log.d("MyTag", "DB ength: " + productDao.getProducts().size)
                return products
            } else {
                Log.d("MyTag", "Not successful Fetching DB")
                return productDao.getProducts()
            }
        } catch (e: Exception) {
            // Fallback to database if API fails
            Log.e("MyTag", "Failed to fetch products from API", e)
            val dbProducts = productDao.getProducts()
            Log.e("MyTag", "length of DB." + dbProducts.size)
            return dbProducts
        }
    }
}