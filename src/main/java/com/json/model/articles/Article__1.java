
package com.json.model.articles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * The article schema
 * <p>
 * An explanation about the purpose of this instance.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "slug",
    "title",
    "description",
    "body",
    "tagList",
    "createdAt",
    "updatedAt",
    "favorited",
    "favoritesCount",
    "author"
})
public class Article__1 {

    /**
     * The slug schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("slug")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String slug = "";
    /**
     * The title schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("title")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    @NotNull
    private String title = "";
    /**
     * The description schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    @NotNull
    private String description = "";
    /**
     * The body schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("body")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    @NotNull
    private String body = "";
    /**
     * The tagList schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("tagList")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    @Valid
    private List<Object> tagList = new ArrayList<Object>();
    /**
     * The createdAt schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("createdAt")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String createdAt = "";
    /**
     * The updatedAt schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("updatedAt")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private String updatedAt = "";
    /**
     * The favorited schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("favorited")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Boolean favorited = false;
    /**
     * The favoritesCount schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("favoritesCount")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    private Long favoritesCount = 0L;
    /**
     * The author schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("author")
    @JsonPropertyDescription("An explanation about the purpose of this instance.")
    @Valid
    private Author author;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * The slug schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    /**
     * The slug schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * The title schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * The title schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The description schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * The description schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The body schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    /**
     * The body schema
     * <p>
     * An explanation about the purpose of this instance.
     * (Required)
     * 
     */
    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * The tagList schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("tagList")
    public List<Object> getTagList() {
        return tagList;
    }

    /**
     * The tagList schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("tagList")
    public void setTagList(List<Object> tagList) {
        this.tagList = tagList;
    }

    /**
     * The createdAt schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * The createdAt schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * The updatedAt schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * The updatedAt schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * The favorited schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("favorited")
    public Boolean getFavorited() {
        return favorited;
    }

    /**
     * The favorited schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("favorited")
    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    /**
     * The favoritesCount schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("favoritesCount")
    public Long getFavoritesCount() {
        return favoritesCount;
    }

    /**
     * The favoritesCount schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("favoritesCount")
    public void setFavoritesCount(Long favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    /**
     * The author schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    /**
     * The author schema
     * <p>
     * An explanation about the purpose of this instance.
     * 
     */
    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Article__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("slug");
        sb.append('=');
        sb.append(((this.slug == null)?"<null>":this.slug));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("body");
        sb.append('=');
        sb.append(((this.body == null)?"<null>":this.body));
        sb.append(',');
        sb.append("tagList");
        sb.append('=');
        sb.append(((this.tagList == null)?"<null>":this.tagList));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("favorited");
        sb.append('=');
        sb.append(((this.favorited == null)?"<null>":this.favorited));
        sb.append(',');
        sb.append("favoritesCount");
        sb.append('=');
        sb.append(((this.favoritesCount == null)?"<null>":this.favoritesCount));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null)?"<null>":this.author));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.tagList == null)? 0 :this.tagList.hashCode()));
        result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
        result = ((result* 31)+((this.author == null)? 0 :this.author.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        result = ((result* 31)+((this.body == null)? 0 :this.body.hashCode()));
        result = ((result* 31)+((this.favoritesCount == null)? 0 :this.favoritesCount.hashCode()));
        result = ((result* 31)+((this.slug == null)? 0 :this.slug.hashCode()));
        result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
        result = ((result* 31)+((this.favorited == null)? 0 :this.favorited.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Article__1) == false) {
            return false;
        }
        Article__1 rhs = ((Article__1) other);
        return ((((((((((((this.tagList == rhs.tagList)||((this.tagList!= null)&&this.tagList.equals(rhs.tagList)))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.author == rhs.author)||((this.author!= null)&&this.author.equals(rhs.author))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.body == rhs.body)||((this.body!= null)&&this.body.equals(rhs.body))))&&((this.favoritesCount == rhs.favoritesCount)||((this.favoritesCount!= null)&&this.favoritesCount.equals(rhs.favoritesCount))))&&((this.slug == rhs.slug)||((this.slug!= null)&&this.slug.equals(rhs.slug))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))))&&((this.favorited == rhs.favorited)||((this.favorited!= null)&&this.favorited.equals(rhs.favorited))));
    }

}
