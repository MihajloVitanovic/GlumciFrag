package com.mvit.mihajlo.glumcifrag.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mihajlo on 17-Jun-17.
 */

@DatabaseTable(tableName = Product.TABLE_NAME_USERS)
public class Product {

    public static final String TABLE_NAME_USERS = "products";

    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "name";
    public static final String FIELD_NAME_LASTNAME   = "lastname";
    public static final String FIELD_NAME_YEAROFBIRTH   = "yearofbirth";
    public static final String FIELD_NAME_DESCRIPTION   = "description";
    public static final String FIELD_NAME_RATING   = "rating";
    public static final String FIELD_NAME_IMAGE  = "image";
    public static final String FIELD_NAME_CATEGORY = "category";
    public static final String FIELD_NAME_MOVIELIST = "movielist";

    public static final String FIELD_NAME_MOVIENAME = "moviename";
    public static final String FIELD_NAME_MOVIEGENRE = "moviegenre";
    public static final String FIELD_NAME_MOVIERELEASEDATE = "moviereleasedate";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    @DatabaseField(columnName = FIELD_NAME_LASTNAME)
    private String mLastName;

    @DatabaseField(columnName = FIELD_NAME_YEAROFBIRTH)
    private String mYearOfBirth;

    @DatabaseField(columnName = FIELD_NAME_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = FIELD_NAME_RATING)
    private float rating;

    @DatabaseField(columnName = FIELD_NAME_IMAGE)
    private String image;

    @DatabaseField(columnName = FIELD_NAME_CATEGORY, foreign = true, foreignAutoCreate = true,foreignAutoRefresh = true)
    private Category category;

    @DatabaseField(columnName = FIELD_NAME_MOVIELIST)
    private String movieList;

    //----------------------------------------------

    @DatabaseField(columnName = FIELD_NAME_MOVIENAME)
    private String movieName;

    @DatabaseField(columnName = FIELD_NAME_MOVIEGENRE)
    private String movieGenre;

    @DatabaseField(columnName = FIELD_NAME_MOVIERELEASEDATE)
    private String movieReleaseDate;

    //ORMLite zahteva prazan konstuktur u klasama koje opisuju tabele u bazi!
    public Product() {
    }

    /** Getters & Setters **/

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmYearOfBirth() {
        return mYearOfBirth;
    }

    public void setmYearOfBirth(String mYearOfBirth) {
        this.mYearOfBirth = mYearOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getMovieList() {
        return movieList;
    }

    public void setMovieList(String movieList) {
        this.movieList = movieList;
    }

    //----------------------------------------------------------------------------

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    //----------------------------------------------------------------------------

    @Override
    public String toString() {
        return  mName;
    }
}