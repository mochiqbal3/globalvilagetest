package com.test.globalvillage.model

import com.gammasolution.segarmart.util.connection.ServiceInterface
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResponseProduct{
    @SerializedName("data")
    var dataProduct = mutableListOf<Product>()
    @SerializedName("success")
    var success = false
    @SerializedName("status")
    var status =0
    companion object{
        val serviceInterface by lazy {
            ServiceInterface.create()
        }

        fun getProduct(limit : Int, start : Int) : Observable<ResponseProduct> {
            return  serviceInterface.getProduct(ServiceInterface.createAuthenticationHeader(),limit,start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

class ResponseProductDetail{
    @SerializedName("data")
    var dataProduct = Product()
    @SerializedName("success")
    var success = false
    @SerializedName("status")
    var status =0
    companion object{
        val serviceInterface by lazy {
            ServiceInterface.create()
        }

        fun getProductDetail(id : String) : Observable<ResponseProduct> {
            return  serviceInterface.getProductDetail(ServiceInterface.createAuthenticationHeader(),id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

class Product{
    @SerializedName("id")
    var id = ""
    @SerializedName("name")
    var name = ""
    @SerializedName("slug")
    var slug = ""
    @SerializedName("price")
    var price = 0
    @SerializedName("price_idr")
    var price_idr = ""
    @SerializedName("stock")
    var stock = 0
    @SerializedName("description")
    var description = ""
    @SerializedName("image_url")
    var image_url = ""
    @SerializedName("user")
    var user = User()
    @SerializedName("category")
    var category = Category()
    @SerializedName("view")
    var view = 0
    @SerializedName("weight")
    var weight = 0
    @SerializedName("unit")
    var unit = Unit()
    @SerializedName("front_images")
    var front_images = mutableListOf<String>()
    @SerializedName("publish_date")
    var publish_date = ""
    @SerializedName("region")
    var region = Region()
}

class User{
    @SerializedName("id")
    var id = ""
    @SerializedName("name")
    var name = ""
    @SerializedName("photo")
    var photo = ""
    @SerializedName("region")
    var region = RegionUser()
}

class RegionUser{
    @SerializedName("village_id")
    var village_id = ""
    @SerializedName("district_id")
    var district_id = ""
    @SerializedName("regency_id")
    var regency_id = ""
    @SerializedName("province_id")
    var province_id = ""
    @SerializedName("village_name")
    var village_name = ""
    @SerializedName("district_name")
    var district_name = ""
    @SerializedName("regency_name")
    var regency_name = ""
    @SerializedName("province_name")
    var province_name = ""
}

class Category{
    @SerializedName("id")
    var id = ""
    @SerializedName("name")
    var name = ""
}

class Unit{
    @SerializedName("name")
    var name = ""
    @SerializedName("id")
    var id = 0
}

class Region{
    @SerializedName("vilage")
    var vilage = ""
    @SerializedName("district")
    var district = ""
    @SerializedName("regency")
    var regency = ""
    @SerializedName("province")
    var province = ""

}