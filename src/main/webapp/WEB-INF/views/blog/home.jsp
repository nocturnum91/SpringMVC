<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="includes/header.jsp" %>
<div class="main-wrapper">
    <section class="cta-section theme-bg-light py-5">
        <div class="container text-center single-col-max-width">
            <h2 class="heading">Nocturnum's DevBlog</h2>
            <div class="single-form-max-width pt-3 mx-auto">
                <form id="pageForm" action="home" method="get" class="signup-form row g-2 g-lg-2 align-items-center">
                    <input type="hidden" name="pageNum" value="<c:out value='${pageMaker.cri.pageNum}'/>">
                    <input type="hidden" name="amount" value="<c:out value='${pageMaker.cri.amount}'/>">
                    <div class="col-12 col-md-9">
                        <input type="text" name="keyword" class="form-control me-md-1 keyword"
                               value="<c:out value='${pageMaker.cri.keyword}'/>">
                    </div>
                    <div class="col-12 col-md-2">
                        <button type="submit" class="btn btn-primary search_btn">Search</button>
                    </div>
                </form><!--//signup-form-->
            </div><!--//single-form-max-width-->
        </div><!--//container-->
    </section>

    <section class="blog-list px-3 py-5 p-md-5">
        <div class="container single-col-max-width">
<%--            <div class="item mb-5">--%>
<%--                <div class="row g-3 g-xl-0">--%>
<%--                    <div class="col-2 col-xl-3">--%>
<%--                        <img class="img-fluid post-thumb " src="../resources/assets/images/blog/blog-post-thumb-1.jpg"--%>
<%--                             alt="image">--%>
<%--                    </div>--%>
<%--                    <div class="col">--%>
<%--                        <h3 class="title mb-1"><a class="text-link" href="blog-post.html">Top 3 JavaScript--%>
<%--                            Frameworks</a></h3>--%>
<%--                        <div class="meta mb-1"><span class="date">Published 2 days ago</span><span class="time">5 min read</span><span--%>
<%--                                class="comment"><a class="text-link" href="#">8 comments</a></span></div>--%>
<%--                        <div class="intro">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo--%>
<%--                            ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient--%>
<%--                            montes, nascetur ridiculus mus. Donec quam felis, ultricies...--%>
<%--                        </div>--%>
<%--                        <a class="text-link" href="blog-post.html">Read more &rarr;</a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
            <c:forEach items="${list}" var="blog">
                <div class="item mb-5">
                    <div class="row g-3 g-xl-0">
                        <div class="col-2 col-xl-3">
                            <img class="img-fluid post-thumb "
                                 src="../resources/assets/images/blog/blog-post-thumb-1.jpg" alt="image">
                        </div>
                        <div class="col">
                            <h3 class="title mb-1"><a class="text-link post-link"
                                                      href="<c:out value='${blog.bno}'/>"><c:out
                                    value="${blog.title}"/></a></h3>
                            <div class="meta mb-1">
                                <span class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                                   value="${blog.regDate}"/></span>
                                <span class="comment"><a class="text-link" href="#">${blog.replyCount} comments</a></span>
                            </div>
                            <div class="intro"><c:out value="${blog.content}"/></div>
                            <a class="text-link post-link" href="<c:out value='${blog.bno}'/>">Read more &rarr;</a>
                        </div><!--//col-->
                    </div><!--//row-->
                </div>
                <!--//item-->
            </c:forEach>
            <div class="d-flex justify-content-center">
                <ul class="pagination">
                    <c:if test="${pageMaker.prev}">
                        <li class="page-item">
                            <a class="page-link" href="${pageMaker.startPage -1}">«</a>
                        </li>
                    </c:if>
                    <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                        <li class="page-item ${pageMaker.cri.pageNum==num ? 'active':''}">
                            <a class="page-link" href="${num}">${num}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${pageMaker.next}">
                        <li class="page-item">
                            <a class="page-link" href="${pageMaker.endPage + 1}">»</a>
                        </li>
                    </c:if>
                </ul>
            </div>
            <%--            <nav class="blog-nav nav nav-justified my-5">--%>
            <%--                <a class="nav-link-prev nav-item nav-link d-none rounded-left" href="#">Previous<i--%>
            <%--                        class="arrow-prev fas fa-long-arrow-alt-left"></i></a>--%>
            <%--                <a class="nav-link-next nav-item nav-link rounded" href="#">Next<i--%>
            <%--                        class="arrow-next fas fa-long-arrow-alt-right"></i></a>--%>
            <%--            </nav>--%>

        </div>
    </section>
    <footer class="footer text-center py-2 theme-bg-dark">
        <!--/* This template is free as long as you keep the footer attribution link. If you'd like to use the template without the attribution link, you can buy the commercial license via our website: themes.3rdwavemedia.com Thank you for your support. :) */-->
        <small class="copyright">Designed with <span class="sr-only">love</span><i class="fas fa-heart"
                                                                                   style="color: #fb866a;"></i> by <a
                href="https://themes.3rdwavemedia.com" target="_blank">Xiaoying Riley</a> for developers</small>
    </footer>
    <div class="toast-container position-fixed top-0 end-0 p-3">
        <div id="resultToast" class="toast align-items-center" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                </div>
                <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>

</div>
<!--//main-wrapper-->
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function () {

        const result = "<c:out value='${result}'/>"
        const method = "<c:out value='${method}'/>"
        const resultToast = document.getElementById('resultToast')
        const toastBody = resultToast.querySelector(".toast-body")
        checkToast(result)

        function checkToast(result) {
            if (result === "") {
                return
            }

            if (parseInt(result) > 0) {
                toastBody.textContent = "포스팅이 등록되었습니다."
                const toast = new bootstrap.Toast(resultToast)
                toast.show()
            }

            if (result === "success") {
                if (method === "modify") {
                    toastBody.textContent = "포스팅이 수정되었습니다."
                } else if (method === "remove") {
                    toastBody.textContent = "포스팅이 삭제되었습니다."
                    history.pushState(null, null, location.href)
                    window.onpopstate = function () {
                        history.go(1)
                    }
                }
                const toast = new bootstrap.Toast(resultToast)
                toast.show()
            }
        }

        const pageForm = document.getElementById("pageForm")
        const pageBtns = document.querySelectorAll(".pagination a")

        pageBtns.forEach((btn) => {
            btn.addEventListener("click", function (e) {
                e.preventDefault()
                pageForm.querySelector("input[name='pageNum']").value = e.target.getAttribute("href")
                if (pageForm.querySelector("input[name='keyword']").value === '') {
                    pageForm.querySelector("input[name='keyword']").remove()
                }
                pageForm.submit()
            })
        })

        const postLinks = document.querySelectorAll(".title a.post-link")

        postLinks.forEach((link) => {
            link.addEventListener("click", function (e) {
                e.preventDefault()
                pageForm.insertAdjacentHTML("afterbegin", "<input type='hidden' name='bno' value='" + e.target.getAttribute("href") + "'>")
                if (pageForm.querySelector("input[name='keyword']").value === '') {
                    pageForm.querySelector("input[name='keyword']").remove()
                }
                pageForm.setAttribute("action", "post")
                pageForm.submit()
            })
        })

        const searchBtn = document.querySelector(".search_btn")
        searchBtn.addEventListener("click", function (e) {
            e.preventDefault()
            pageForm.querySelector("input[name='pageNum']").value = 1
            pageForm.submit()
        })


    })
</script>
<%@include file="includes/footer.jsp" %>
