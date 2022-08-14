package com.fom.retrovm.models;

import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public class User implements Serializable {

    @SerializedName("id")
    public int id;
    @SerializedName("login")
    public String login;
    @SerializedName("avatar_url")
    public String avatar_url;
    @SerializedName("gravatar_id")
    public String gravatar_id;
    @SerializedName("url")
    public String url;
    @SerializedName("html_url")
    public String html_url;
    @SerializedName("followers_url")
    public String followers_url;
    @SerializedName("following_url")
    public String following_url;
    @SerializedName("gists_url")
    public String gists_url;
    @SerializedName("starred_url")
    public String starred_url;
    @SerializedName("subscriptions_url")
    public String subscriptions_url;
    @SerializedName("organizations_url")
    public String organizations_url;
    @SerializedName("repos_url")
    public String repos_url;
    @SerializedName("events_url")
    public String events_url;
    @SerializedName("received_events_url")
    public String received_events_url;
    @SerializedName("type")
    public String type;
    @SerializedName("site_admin")
    public boolean site_admin;
    @SerializedName("name")
    public String name;
    @SerializedName("company")
    public String company;
    @SerializedName("blog")
    public String blog;
    @SerializedName("location")
    public String location;
    @SerializedName("email")
    public String email;
    @SerializedName("hireable")
    public String hireable;
    @SerializedName("bio")
    public String bio;
    @SerializedName("twitter_username")
    public String twitter_username;
    @SerializedName("public_repos")
    public int public_repos;
    @SerializedName("public_gists")
    public int public_gists;
    @SerializedName("followers")
    public int followers;
    @SerializedName("following")
    public int following;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;

    public User() {
    }

    @BindingAdapter("avatar")
    public static void setAvatarView(ImageView view, String avatar_url) {
        Glide.with(view)
                .load(avatar_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    @BindingAdapter("avatarInCircle")
    public static void setAvatarInCircleView(ImageView view, String avatar_url) {
        Glide.with(view)
                .load(avatar_url)
                .transform(new CircleCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    @BindingAdapter({"text1", "text2"})
    public static void setText(TextView view, String text1, String text2) {
        String text = text1 != null ? text1 : text2;
        view.setText(text);
    }

    @BindingAdapter({"htmlText", "count"})
    public static void setHtmlText(TextView view, String text, int count) {
        String html = "<font color=\"#000000\"><big><b>" + count + "</b></big></font>"
                + "<br>"
                + "<font color=\"#80000000\"><small>" + text + "</small></font>";

        view.setText(Html.fromHtml(html));
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", gravatar_id='" + gravatar_id + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", followers_url='" + followers_url + '\'' +
                ", following_url='" + following_url + '\'' +
                ", gists_url='" + gists_url + '\'' +
                ", starred_url='" + starred_url + '\'' +
                ", subscriptions_url='" + subscriptions_url + '\'' +
                ", organizations_url='" + organizations_url + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", events_url='" + events_url + '\'' +
                ", received_events_url='" + received_events_url + '\'' +
                ", type='" + type + '\'' +
                ", site_admin=" + site_admin +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", blog='" + blog + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", hireable='" + hireable + '\'' +
                ", bio='" + bio + '\'' +
                ", twitter_username='" + twitter_username + '\'' +
                ", public_repos=" + public_repos +
                ", public_gists=" + public_gists +
                ", followers=" + followers +
                ", following=" + following +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
