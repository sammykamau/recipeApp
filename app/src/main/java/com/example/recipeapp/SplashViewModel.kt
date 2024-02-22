package com.example.recipeapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.entities.Category
import com.example.recipeapp.interfaces.GetDataService
import com.example.recipeapp.retrofitclient.RetrofitClientInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class SplashViewModel(application: Application) : AndroidViewModel(application) {


    private val _categories = MutableLiveData<Category>()
    val categories: LiveData<Category> get() = _categories

    init {
        // Initialize the database in the constructor using the application context
        RecipeDatabase.getDatabase(application.applicationContext)
    }

    suspend fun fetchData() {
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getCategoryList()
        call.enqueue(object : retrofit2.Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                _categories.postValue(response.body())
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                // Handle failure
            }
        })
    }

    suspend fun insertDataIntoRoomDb(category: Category) {
        withContext(Dispatchers.IO) {
            RecipeDatabase.getDatabase(getApplication()).recipeDao().clearDb()
            for (arr in category.categoriesitems!!) {
                RecipeDatabase.getDatabase(getApplication()).recipeDao().insertCategory(arr)
            }
        }
    }
}