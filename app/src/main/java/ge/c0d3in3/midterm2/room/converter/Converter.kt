package ge.c0d3in3.midterm2.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import ge.c0d3in3.midterm2.presentation.users.model.Address
import ge.c0d3in3.midterm2.presentation.users.model.Company

class Converter {

    val gson = Gson()

    @TypeConverter
    fun companyToJson(company: Company) : String {
        return gson.toJson(company)
    }

    @TypeConverter
    fun jsonToCompany(json: String) : Company {
        return gson.fromJson(json, Company::class.java)
    }

    @TypeConverter
    fun addressToJson(address: Address) : String {
        return gson.toJson(address)
    }

    @TypeConverter
    fun jsonToAddress(json: String) : Address {
        return gson.fromJson(json, Address::class.java)
    }
}