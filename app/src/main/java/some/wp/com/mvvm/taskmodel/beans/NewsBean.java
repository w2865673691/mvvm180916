package some.wp.com.mvvm.taskmodel.beans;

import some.wp.com.mvvm.taskmodel.BaseBean;


public class NewsBean extends BaseBean {

    private int limit;
    private boolean isGood;
    private String name;
    private String description;
    private String url;

    //
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
