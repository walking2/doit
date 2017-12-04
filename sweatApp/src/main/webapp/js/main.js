$("#tab_btn").children().click(function(){
    $(this).addClass("tab_active").siblings().removeClass("tab_active");
    $(this).parents().siblings(".tab_content").children().eq($(this).attr("index")).addClass("tab_content_active").siblings().removeClass("tab_content_active")
});