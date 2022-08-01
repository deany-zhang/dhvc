package com.dhvc.home

import android.os.Parcel
import android.os.Parcelable


data class DataBeanItem(
    val calories: String?,
    val carbos: String?,
    val country: String?,
    val description: String?,
    val difficulty: Int,
    val fats: String?,
    val headline: String?,
    val id: String?,
    val image: String?,
    val name: String?,
    val proteins: String?,
    val thumb: String?,
    val time: String?

) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(calories)
        parcel.writeString(carbos)
        parcel.writeString(country)
        parcel.writeString(description)
        parcel.writeInt(difficulty)
        parcel.writeString(fats)
        parcel.writeString(headline)
        parcel.writeString(id)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(proteins)
        parcel.writeString(thumb)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataBeanItem> {
        override fun createFromParcel(parcel: Parcel): DataBeanItem {
            return DataBeanItem(parcel)
        }

        override fun newArray(size: Int): Array<DataBeanItem?> {
            return arrayOfNulls(size)
        }
    }
}