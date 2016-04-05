package com.example.kairo.testapp.models;

import java.util.List;

/**
 * Created by kairo on 4/5/2016.
 */
public class Videoz {
    /**
     * videos : [{"video":"https://www.youtube.com/watch?v=aVS4W7GZSq0","nombre":"Video 1"},{"video":"https://www.youtube.com/watch?v=kPDnw3_1GOI","nombre":"Video 2"},{"video":"https://www.youtube.com/watch?v=dRjE1JwdDLI","nombre":"Video 3"},{"video":"https://vimeo.com/50713841","nombre":"Video 4"},{"video":"https://www.youtube.com/watch?v=Y4zfkeTsto0","nombre":"Video 5"},{" video":"https://www.youtube.com/watch?v=2lWJQmCf2CY&list=RDY4zfkeTsto0&index=2","nombre":"Video 6"},{"video":"https://www.youtube.com/watch?v=CZLEQKM1neE&list=RDY4zfkeTsto0&index=4","nombre":"Video 7"}]
     * total : 7
     */

    private int total;
    /**
     * video : https://www.youtube.com/watch?v=aVS4W7GZSq0
     * nombre : Video 1
     */

    private List<VideosBean> videos;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean {
        private String video;
        private String nombre;

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }
}
