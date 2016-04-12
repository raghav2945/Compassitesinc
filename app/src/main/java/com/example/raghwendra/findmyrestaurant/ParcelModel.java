package com.example.raghwendra.findmyrestaurant;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by raghawendra.kumar on 12-04-2016.
 */
public class ParcelModel implements Parcelable {
    String restaurant_name;
    String address;
    String latitude;
    String longitude;
    String phone_number;
    String logo_url;

    public ParcelModel() {
    }

    public ParcelModel(String restaurant_name, String address, String latitude, String longitude, String phone_number, String logo_url) {
        this.restaurant_name = restaurant_name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone_number = phone_number;
        this.logo_url = logo_url;
    }



    protected ParcelModel(Parcel in) {
        restaurant_name = in.readString();
        address = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        phone_number = in.readString();
        logo_url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(restaurant_name);
        dest.writeString(address);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(phone_number);
        dest.writeString(logo_url);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ParcelModel> CREATOR = new Parcelable.Creator<ParcelModel>() {
        @Override
        public ParcelModel createFromParcel(Parcel in) {
            return new ParcelModel(in);
        }

        @Override
        public ParcelModel[] newArray(int size) {
            return new ParcelModel[size];
        }
    };

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
