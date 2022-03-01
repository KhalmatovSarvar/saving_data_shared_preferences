package com.example.saving_data_shared_preferences

import android.os.Parcel
import android.os.Parcelable

data class User(
    var name: String? = null,
    var surname: String? = null,
    var age: Int = 0,
    var money: Float = 0f,
    var email: String? = null,
    var isMarried: Boolean = false,
    var password: String? = null,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeInt(age)
        parcel.writeFloat(money)
        parcel.writeString(email)
        parcel.writeByte(if (isMarried) 1 else 0)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}