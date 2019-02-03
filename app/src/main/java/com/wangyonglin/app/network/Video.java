package com.wangyonglin.app.network;



import java.util.Date;

public class Video  {

        private String uuid;

        private String title;

        private String video;

        private String image;

        private String categories;

        private String overview;

        private Date datetime;

        private Boolean enabled;
        public String getUuid() {
            return uuid;
        }
        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getVideo() {
            return video;
        }
        public void setVideo(String video) {
            this.video = video;
        }
        public String getImage() {
            return image;
        }
        public void setImage(String image) {
            this.image = image;
        }
        public String getCategories() {
            return categories;
        }
        public void setCategories(String categories) {
            this.categories = categories;
        }
        public Date getDatetime() {
            return datetime;
        }
        public void setDatetime(Date datetime) {
            this.datetime = datetime;
        }
        public Boolean getEnabled() {
            return enabled;
        }
        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
        public String getOverview() {
            return overview;
        }
        public void setOverview(String overview) {
            this.overview = overview;
        }



}
