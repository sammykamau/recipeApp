package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.adapter.MainCategoryAdapter
import com.example.recipeapp.adapter.SubCategoryAdapter
import com.example.recipeapp.entities.Recipes


class HomeActivity : AppCompatActivity() {
    var arrMainCategory = ArrayList<Recipes>()
    var arrSubCategory = ArrayList<Recipes>()
    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        arrMainCategory.add(Recipes(1, "Beef"))
        arrMainCategory.add(Recipes(2, "Chapo"))
        arrMainCategory.add(Recipes(3, "Rice"))
        arrMainCategory.add(Recipes(4, "Chicken"))

        mainCategoryAdapter.setData(arrMainCategory)

        arrSubCategory.add(Recipes(1, "Nyama choma na kachumbari"))
        arrSubCategory.add(Recipes(2, "Chapo mix"))
        arrSubCategory.add(Recipes(3, "Rice beans"))
        arrSubCategory.add(Recipes(4, "Chicken and fries"))

        subCategoryAdapter.setData(arrSubCategory)

        val recyclerViewMain: RecyclerView = findViewById(R.id.rv_main_category)
        recyclerViewMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewMain.adapter = mainCategoryAdapter

        val recyclerViewSub: RecyclerView = findViewById(R.id.rv_sub_category)
        recyclerViewSub.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewSub.adapter = subCategoryAdapter
    }
}