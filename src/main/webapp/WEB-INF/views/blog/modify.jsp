<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="includes/header.jsp" %>
<div class="main-wrapper">
    <section class="modify-section theme-bg-light py-5">
        <div class="container text-center single-col-max-width">
            <h2 class="heading">DevBlog</h2>
            <div class="single-form-max-width pt-3 mx-auto">
                <form id="modifyForm" action="modify" method="post"
                      class="register-form row g-2 g-lg-2 align-items-center">
                    <input type="hidden" name="bno" value="<c:out value='${blog.bno}'/>"/>
                    <input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
                    <input type="hidden" name="amount" value="<c:out value='${cri.amount}'/>">
                    <input type="hidden" name="keyword" value="<c:out value='${cri.keyword}'/>">
                    <div class="form-group col-12">
                        <label class="sr-only" for="title">Title</label>
                        <input type="text" id="title" name="title" class="form-control me-md-1 title"
                               value="<c:out value='${blog.title}'/>">
                    </div>
                    <div class="form-group col-12">
                        <label class="sr-only" for="content">Content</label>
                        <textarea id="content" name="content" class="form-control content" rows="10"><c:out
                                value='${blog.content}'/></textarea>
                    </div>
                    <div class="form-group gap-2 d-md-flex justify-content-md-end">
                        <button type="submit" class="btn btn-primary modify_btn" data-oper="modify">Modify</button>
                        <button type="submit" class="btn btn-primary cancel_btn" data-oper="cancel">Cancel</button>
                    </div>
                </form><!--//signup-form-->
            </div><!--//single-form-max-width-->
        </div><!--//container-->
    </section>
    <footer class="footer text-center py-2 theme-bg-dark">
        <!--/* This template is free as long as you keep the footer attribution link. If you'd like to use the template without the attribution link, you can buy the commercial license via our website: themes.3rdwavemedia.com Thank you for your support. :) */-->
        <small class="copyright">Designed with <span class="sr-only">love</span><i class="fas fa-heart"
                                                                                   style="color: #fb866a;"></i> by <a
                href="https://themes.3rdwavemedia.com" target="_blank">Xiaoying Riley</a> for developers</small>
    </footer>
</div>
<!--//main-wrapper-->
<%@include file="includes/footer.jsp" %>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function () {
        const modifyForm = document.getElementById("modifyForm")
        const buttons = document.querySelectorAll(".main-wrapper button[type='submit']")
        const hiddenInputs = modifyForm.querySelectorAll("[type='hidden']")


        buttons.forEach((button) => {
            button.addEventListener("click", function (e) {
                e.preventDefault()
                const operation = button.dataset.oper
                if (operation === "modify") {
                    if (modifyForm.querySelector("input[name='keyword']") !== undefined && modifyForm.querySelector("input[name='keyword']").value === "") {
                        modifyForm.querySelector("input[name='keyword']").remove()
                    }
                    modifyForm.submit()
                } else if (operation === "cancel") {
                    history.back()
                }
                /* LIST로 돌아가는 버튼을 만들려면 아래 주석 참고 */
                // if (operation === "list") {
                //     modifyForm.setAttribute("action", "home")
                //     modifyForm.setAttribute("method", "get")
                //     const inputs = modifyForm.querySelectorAll("input, textarea")
                //     inputs.forEach((input) => {
                //         if (!(input.getAttribute("name") === "pageNum" || input.getAttribute("name") === "amount") || input.getAttribute("name") === "keyword")) {
                //             input.remove()
                //         }
                //     })
                // }

            })
        })
    })
</script>