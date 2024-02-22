package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.adapter.MainCategoryAdapter
import com.example.recipeapp.adapter.SubCategoryAdapter
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.entities.CategoryItems
import com.example.recipeapp.entities.MealsItems
import com.example.recipeapp.entities.Recipes
import kotlinx.coroutines.launch


class HomeActivity : BaseActivity() {
    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealsItems>()
    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getDataFromDb()

        mainCategoryAdapter.setClickListener(onCLicked)

    }

    private val onCLicked  = object : MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

//    private val onCLickedSubItem  = object : SubCategoryAdapter.OnItemClickListener{
//        override fun onClicked(id: String) {
//            var intent = Intent(this@HomeActivity,DetailActivity::class.java)
//            intent.putExtra("id",id)
//            startActivity(intent)
//        }
//    }


    private fun getDataFromDb(){
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()
                mainCategoryAdapter.setData(arrMainCategory)

                val recyclerViewMain: RecyclerView = findViewById(R.id.rv_main_category)
                recyclerViewMain.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewMain.adapter = mainCategoryAdapter

                // by default display the sub category
                getMealDataFromDb(arrMainCategory[0].strCategory)
            }
        }
    }

    private fun getMealDataFromDb(categoryName:String){
        val tvName: TextView = findViewById(R.id.tvCategory)
        tvName.text = "$categoryName Category"
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealsItems>
                subCategoryAdapter.setData(arrSubCategory)

                val recyclerViewSub: RecyclerView = findViewById(R.id.rv_sub_category)
                recyclerViewSub.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewSub.adapter = subCategoryAdapter
            }


        }
    }
}