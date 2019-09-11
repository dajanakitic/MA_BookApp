
package com.bookapp.bookapp.remote.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author implements Parcelable {

    @SerializedName("links")
    @Expose
    private List<Link> links = null;
    @SerializedName("covers")
    @Expose
    private List<Integer> covers = null;
    @SerializedName("lc_classifications")
    @Expose
    private List<String> lcClassifications = null;
    @SerializedName("latest_revision")
    @Expose
    private Integer latestRevision;
    @SerializedName("ocaid")
    @Expose
    private String ocaid;
    @SerializedName("edition_name")
    @Expose
    private String editionName;
    @SerializedName("source_records")
    @Expose
    private List<String> sourceRecords = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("languages")
    @Expose
    private List<Language> languages = null;
    @SerializedName("subjects")
    @Expose
    private List<String> subjects = null;
    @SerializedName("publish_country")
    @Expose
    private String publishCountry;
    @SerializedName("by_statement")
    @Expose
    private String byStatement;
    @SerializedName("oclc_numbers")
    @Expose
    private List<String> oclcNumbers = null;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("revision")
    @Expose
    private Integer revision;
    @SerializedName("publishers")
    @Expose
    private List<String> publishers = null;
    @SerializedName("physical_format")
    @Expose
    private String physicalFormat;
    @SerializedName("full_title")
    @Expose
    private String fullTitle;
    @SerializedName("last_modified")
    @Expose
    private LastModified lastModified;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("authors")
    @Expose
    private List<Author_> authors = null;
    @SerializedName("publish_places")
    @Expose
    private List<String> publishPlaces = null;
    @SerializedName("pagination")
    @Expose
    private String pagination;
    @SerializedName("created")
    @Expose
    private Created created;
    @SerializedName("dewey_decimal_class")
    @Expose
    private List<String> deweyDecimalClass = null;
    @SerializedName("notes")
    @Expose
    private Notes notes;
    @SerializedName("isbn_13")
    @Expose
    private List<String> isbn13 = null;
    @SerializedName("isbn_10")
    @Expose
    private List<String> isbn10 = null;
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @SerializedName("works")
    @Expose
    private List<Work> works = null;

    protected Author(Parcel in) {
        lcClassifications = in.createStringArrayList();
        if (in.readByte() == 0) {
            latestRevision = null;
        } else {
            latestRevision = in.readInt();
        }
        ocaid = in.readString();
        editionName = in.readString();
        sourceRecords = in.createStringArrayList();
        title = in.readString();
        subjects = in.createStringArrayList();
        publishCountry = in.readString();
        byStatement = in.readString();
        oclcNumbers = in.createStringArrayList();
        if (in.readByte() == 0) {
            revision = null;
        } else {
            revision = in.readInt();
        }
        publishers = in.createStringArrayList();
        physicalFormat = in.readString();
        fullTitle = in.readString();
        key = in.readString();
        publishPlaces = in.createStringArrayList();
        pagination = in.readString();
        deweyDecimalClass = in.createStringArrayList();
        isbn13 = in.createStringArrayList();
        isbn10 = in.createStringArrayList();
        publishDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(lcClassifications);
        if (latestRevision == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(latestRevision);
        }
        dest.writeString(ocaid);
        dest.writeString(editionName);
        dest.writeStringList(sourceRecords);
        dest.writeString(title);
        dest.writeStringList(subjects);
        dest.writeString(publishCountry);
        dest.writeString(byStatement);
        dest.writeStringList(oclcNumbers);
        if (revision == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(revision);
        }
        dest.writeStringList(publishers);
        dest.writeString(physicalFormat);
        dest.writeString(fullTitle);
        dest.writeString(key);
        dest.writeStringList(publishPlaces);
        dest.writeString(pagination);
        dest.writeStringList(deweyDecimalClass);
        dest.writeStringList(isbn13);
        dest.writeStringList(isbn10);
        dest.writeString(publishDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Integer> getCovers() {
        return covers;
    }

    public void setCovers(List<Integer> covers) {
        this.covers = covers;
    }

    public List<String> getLcClassifications() {
        return lcClassifications;
    }

    public void setLcClassifications(List<String> lcClassifications) {
        this.lcClassifications = lcClassifications;
    }

    public Integer getLatestRevision() {
        return latestRevision;
    }

    public void setLatestRevision(Integer latestRevision) {
        this.latestRevision = latestRevision;
    }

    public String getOcaid() {
        return ocaid;
    }

    public void setOcaid(String ocaid) {
        this.ocaid = ocaid;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public List<String> getSourceRecords() {
        return sourceRecords;
    }

    public void setSourceRecords(List<String> sourceRecords) {
        this.sourceRecords = sourceRecords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public String getPublishCountry() {
        return publishCountry;
    }

    public void setPublishCountry(String publishCountry) {
        this.publishCountry = publishCountry;
    }

    public String getByStatement() {
        return byStatement;
    }

    public void setByStatement(String byStatement) {
        this.byStatement = byStatement;
    }

    public List<String> getOclcNumbers() {
        return oclcNumbers;
    }

    public void setOclcNumbers(List<String> oclcNumbers) {
        this.oclcNumbers = oclcNumbers;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public String getPhysicalFormat() {
        return physicalFormat;
    }

    public void setPhysicalFormat(String physicalFormat) {
        this.physicalFormat = physicalFormat;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public LastModified getLastModified() {
        return lastModified;
    }

    public void setLastModified(LastModified lastModified) {
        this.lastModified = lastModified;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Author_> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author_> authors) {
        this.authors = authors;
    }

    public List<String> getPublishPlaces() {
        return publishPlaces;
    }

    public void setPublishPlaces(List<String> publishPlaces) {
        this.publishPlaces = publishPlaces;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public Created getCreated() {
        return created;
    }

    public void setCreated(Created created) {
        this.created = created;
    }

    public List<String> getDeweyDecimalClass() {
        return deweyDecimalClass;
    }

    public void setDeweyDecimalClass(List<String> deweyDecimalClass) {
        this.deweyDecimalClass = deweyDecimalClass;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public List<String> getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(List<String> isbn13) {
        this.isbn13 = isbn13;
    }

    public List<String> getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(List<String> isbn10) {
        this.isbn10 = isbn10;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

}
