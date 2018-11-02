package com.example.gabrielsaruhashi.ramp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Masayuki on 9/21/18.
 * https://guides.codepath.com/android/converting-json-to-models
 */

public class Place implements Parcelable{
    private int id;
    private double lat;
    private double lon;
    private String name;
    private String hours;
    private String description;
    private String address;
    private String phoneNumer;
    private String walkInsAccepted;

    public int getId(){
        return this.id;
    }

    public double getLat() {return this.lat; }

    public double getLon() {return this.lon; }

    public String getName() { return name; }

    public String getHours() { return hours; }

    public String getDescription() { return description; }

    public String getAddress() { return address; }

    public String getPhoneNumer() { return phoneNumer; }

    public String getWalkIns() { return walkInsAccepted; }

    public String toPrint(){ return "id: " + Integer.toString(id) + " latitude: " + Double.toString(lat) + " longitude: " + Double.toString(lon); }

    //To allow for parcelling:
    //https://stackoverflow.com/questions/7181526/how-can-i-make-my-custom-objects-parcelable
    //http://prasanta-paul.blogspot.com/2010/06/android-parcelable-example.html
    public Place(Parcel in){
        id = in.readInt();
        lat = in.readDouble();
        lon = in.readDouble();
        name = in.readString();
        hours = in.readString();
        description = in.readString();
        address = in.readString();
        phoneNumer = in.readString();
        walkInsAccepted = in.readString();
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeString(name);
        dest.writeString(hours);
        dest.writeString(description);
        dest.writeString(address);
        dest.writeString(phoneNumer);
        dest.writeString(walkInsAccepted);
    }

    public Place(){
        this.id = 0;
        this.lat = 0;
        this.lon = 0;
        this.name = null;
        this.hours = null;
        this.description = null;
        this.address = null;
        this.phoneNumer = null;
        this.walkInsAccepted = null;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public static Place fromJson(JSONObject jsonObject){
        Place newPlace = new Place();
        try{
            JSONObject programS = jsonObject.getJSONObject("program_site");
            newPlace.id = programS.getInt("id");

            JSONObject program = programS.getJSONObject("program");
            newPlace.name = program.getString("name");
            newPlace.description = program.getString("description");
            newPlace.walkInsAccepted = program.getString("walk_ins_accepted");

            JSONObject address = programS.getJSONObject("address");
            newPlace.address = address.getString("address_1");
            JSONArray coordinates = address.getJSONArray("coords");
            newPlace.lat = coordinates.getDouble(1);
            newPlace.lon = coordinates.getDouble(0);

            JSONObject phones = programS.getJSONObject("phones");
            newPlace.phoneNumer = phones.getString("phone_1");

            newPlace.hours = programS.getString("hours");



        }catch (JSONException e) {
            Log.d("search", "failed");
            e.printStackTrace();
            return null;
        }
        return newPlace;
    }

    public static ArrayList<Place> fromJson(JSONArray jsonArray){
        JSONObject placesJson;
        ArrayList<Place> places = new ArrayList<Place>(jsonArray.length());
        for(int i = 0; i < jsonArray.length(); i++){
            try{
                placesJson = jsonArray.getJSONObject(i);
            }catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Place place = Place.fromJson(placesJson);
            if(place != null){
                places.add(place);
            }

        }
        return places;
    }

}
