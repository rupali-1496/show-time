package com.example.showtime.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentNodes {

    @SerializedName("id")
    public Integer id;
    @SerializedName("page")
    public Integer page;
    @SerializedName("total_results")
    public Integer total;
    @SerializedName("total_pages")
    public Integer totalPages;
    @SerializedName("results")
    public List<Datum> results = null;

    public class Datum {

        @SerializedName("description")
        public String description;
        @SerializedName("favorite_count")
        public Integer favorite_count;
        @SerializedName("id")
        public Integer id;
        @SerializedName("item_count")
        public Integer item_count;
        @SerializedName("iso_639_1")
        public String iso_639_1;
        @SerializedName("list_type")
        public String list_type;
        @SerializedName("name")
        public String name;
        @SerializedName("poster_path")
        public String poster_path;

    }
}


/*
    @SerializedName("page")
    public Integer page;
    @SerializedName("per_page")
    public Integer perPage;
    @SerializedName("total")
    public Integer total;
    @SerializedName("total_pages")
    public Integer totalPages;
    @SerializedName("data")
    public List<Datum> data = null;

    public class Datum {

        @SerializedName("id")
        public Integer id;
        @SerializedName("name")
        public String name;
        @SerializedName("year")
        public Integer year;
        @SerializedName("pantone_value")
        public String pantoneValue;

    }

 */

