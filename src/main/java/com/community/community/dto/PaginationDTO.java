package com.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回层
 */
@Data
public class PaginationDTO {


    private List<QuestionDTO> questions;

    /**
     * 是否有向前按钮
     */
    private boolean showPrevious;

    /**
     * 是否有第一页按钮
     */
    private boolean showFirstPage;

    /**
     * 是否有向下按钮
     */
    private boolean showNext;

    /**
     * 是否有最后一页按钮
     */
    private boolean showEndPage;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 页码集合
     */
    private List<Integer> pages=new ArrayList<>();

    /**
     * 总页数
     */
    private Integer totalPage;

    public void setPaginationDTO(int totalCount, Integer page, Integer pageSize) {

        if (totalCount % pageSize==0){
            this.totalPage=totalCount / pageSize;
        }else {
            this.totalPage=(totalCount / pageSize)+1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        this.page=page;
        pages.add(page);
        for (int i = 1; i <=3 ; i++) {
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page+i<totalPage){
                pages.add(page+i);
            }
        }

        if (page==1){
            showPrevious=false;
        }else {
            showPrevious=true;
        }
        if (page==totalPage){
            showNext=false;
        }else {
            showNext=true;
        }
        if (pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        if (pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }
    }
}
