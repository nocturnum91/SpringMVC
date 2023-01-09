<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="includes/header.jsp" %>
<div class="main-wrapper">
    <article class="blog-post px-3 py-5 p-md-5">
        <div class="container single-col-max-width">
            <c:if test="${not empty blog.bno}">
                <header class="blog-post-header">
                    <div class="post-title"><h2 class="title mb-2"><c:out value="${blog.title}"/></h2>
                        <button type="submit" id="editBtn" class="edit_btn" data-oper="edit">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                <path fill-rule="evenodd"
                                      d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                            </svg>
                        </button>
                        <button type="submit" id="delCheckBtn" class="delete_check_btn" data-oper="deleteCheck">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-trash3" viewBox="0 0 16 16">
                                <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                            </svg>
                        </button>
                    </div>
                    <div class="meta mb-3">
                        <span class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${blog.regDate}"/></span>
                        <span class="comment"><a class="text-link" href="#">4 comments</a></span>

                    </div>
                </header>

                <div class="blog-post-body">
                    <p><c:out value="${blog.content}"/></p>
                </div>

                <nav class="blog-nav nav nav-pills nav-justified my-5">
                    <a class="nav-link-prev nav-item nav-link rounded-left" data-oper="prev"
                       data-bno="<c:out value='${preBno}'/>">Previous<i
                            class="arrow-prev fas fa-long-arrow-alt-left"></i></a>
                    <a class="nav-link-next nav-item nav-link rounded-right" data-oper="next"
                       data-bno="<c:out value='${nextBno}'/>">Next<i
                            class="arrow-next fas fa-long-arrow-alt-right"></i></a>
                </nav>


                <div class="blog-comments-section">
                    <ul class="comment-list list-unstyled">
                        <li>
                            <div class="comment">
                                <div class="meta">
                                    <span class="replier">Noc</span>
                                    <span class="date">2023-01-10 00:30</span>
                                </div>
                                <p>Hello!</p>
                            </div>
                        </li>
                    </ul>
                </div>
                <!--//blog-comments-section-->
            </c:if>
            <c:if test="${empty blog.bno}">
                <h3>존재하지 않는 게시글입니다.</h3>
            </c:if>
        </div><!--//container-->
    </article>

    <%--    <section class="promo-section theme-bg-light py-5 text-center">--%>
    <%--        <div class="container">--%>
    <%--            <h2 class="title">Promo Section Heading</h2>--%>
    <%--            <p>You can use this section to promote your side projects etc. Lorem ipsum dolor sit amet, consectetuer--%>
    <%--                adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. </p>--%>
    <%--            <figure class="promo-figure">--%>
    <%--                <a href="https://made4dev.com" target="_blank"><img class="img-fluid"--%>
    <%--                                                                    src="../resources/assets/images/promo-banner.jpg"--%>
    <%--                                                                    alt="image"></a>--%>
    <%--            </figure>--%>
    <%--        </div><!--//container-->--%>
    <%--    </section><!--//promo-section-->--%>

    <footer class="footer text-center py-2 theme-bg-dark">

        <!--/* This template is free as long as you keep the footer attribution link. If you'd like to use the template without the attribution link, you can buy the commercial license via our website: themes.3rdwavemedia.com Thank you for your support. :) */-->
        <small class="copyright">Designed with <span class="sr-only">love</span><i class="fas fa-heart"
                                                                                   style="color: #fb866a;"></i> by <a
                href="https://themes.3rdwavemedia.com" target="_blank">Xiaoying Riley</a> for developers</small>

    </footer>
    <div id="deleteModal" class="modal fade">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"></span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>포스팅을 삭제하시겠습니까?</p>
                </div>
                <div class="modal-footer">
                    <button type="submit" id="removeBtn" class="btn btn-primary remove_btn" data-oper="remove">Remove
                    </button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <div class="toast-container position-fixed top-0 end-0 p-3">
        <div id="resultToast" class="toast align-items-center" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                </div>
                <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
    <form id="pageForm" action="" method="">
        <input type="hidden" name="bno" value="<c:out value='${blog.bno}'/>">
        <input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
        <input type="hidden" name="amount" value="<c:out value='${cri.amount}'/>">
        <input type="hidden" name="keyword" value="<c:out value='${cri.keyword}'/>">
    </form>
</div>
<!--//main-wrapper-->
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function () {

        const bno = "<c:out value='${blog.bno}'/>"
        const deleteModal = document.getElementById("deleteModal")
        const modal = new bootstrap.Modal(deleteModal)
        const pageForm = document.getElementById("pageForm")
        const buttons = document.querySelectorAll(".main-wrapper button[type='submit']")

        buttons.forEach((button) => {
            button.addEventListener("click", function (e) {
                e.preventDefault()
                pageForm.querySelector("input[name='bno']").value = bno
                const operation = button.dataset.oper
                if (operation === "edit") {
                    pageForm.setAttribute("action", "modify")
                    pageForm.setAttribute("method", "get")
                    if (pageForm.querySelector("input[name='keyword']") !== undefined && pageForm.querySelector("input[name='keyword']").value === "") {
                        pageForm.querySelector("input[name='keyword']").remove()
                    }
                    pageForm.submit()
                } else if (operation === "deleteCheck") {
                    modal.show()
                } else if (operation === "remove") {
                    pageForm.setAttribute("action", "remove")
                    pageForm.setAttribute("method", "post")
                    modal.hide()
                    pageForm.submit()
                }
            })
        })

        const preBtn = document.querySelector(".blog-nav .rounded-left")
        const nextBtn = document.querySelector(".blog-nav .rounded-right")
        const resultToast = document.getElementById('resultToast')
        const toastBody = resultToast.querySelector(".toast-body")

        preBtn.addEventListener("click", function (e) {
            e.preventDefault()
            const preBno = e.target.dataset.bno
            const operation = e.target.dataset.oper
            if (preBno === "") {
                checkToast(operation)
            } else {
                pageForm.querySelector("input[name='bno']").value = preBno
                if (pageForm.querySelector("input[name='keyword']") !== undefined && pageForm.querySelector("input[name='keyword']").value === "") {
                    pageForm.querySelector("input[name='keyword']").remove()
                }
                pageForm.setAttribute("action", "post")
                pageForm.setAttribute("method", "get")
                pageForm.submit()
            }
        })

        nextBtn.addEventListener("click", function (e) {
            e.preventDefault()
            const nextBno = e.target.dataset.bno
            const operation = e.target.dataset.oper
            if (nextBno === "") {
                checkToast(operation)
            } else {
                pageForm.querySelector("input[name='bno']").value = nextBno
                if (pageForm.querySelector("input[name='keyword']") !== undefined && pageForm.querySelector("input[name='keyword']").value === "") {
                    pageForm.querySelector("input[name='keyword']").remove()
                }
                pageForm.setAttribute("action", "post")
                pageForm.setAttribute("method", "get")
                pageForm.submit()
            }

        })

        function checkToast(operation) {
            if (operation === "prev") {
                toastBody.textContent = "이전글이 없습니다."
            } else if (operation === "next") {
                toastBody.textContent = "다음글이 없습니다."
            }
            const toast = new bootstrap.Toast(resultToast)
            toast.show()
        }

        const commentList = document.querySelector(".comment-list")

        // function showList(page) {
        //     replyService.getList(
        //         {bno: bno, page: 1},
        //         function (list) {
        //             for (let i = 0, len = list.length || 0; i < len; i++) {
        //                 console.log(list[i])
        //             }
        //         }
        //     )
        // }

        //for replyService add test
        // replyService.add(
        //     {reply:"댓글 입력 테스트", replier:"Noc", bno:bno},
        //     function (result) {
        //         console.log("RESULT: " + result)
        //     }
        // )

        //for replyService getList test
        // replyService.getList(
        //     {bno: bno, page: 1},
        //     function (list) {
        //         for (let i = 0, len = list.length || 0; i < len; i++) {
        //             console.log(list[i])
        //         }
        //     }
        // )

        //for replyService update test
        // replyService.update(
        //     {
        //         bno: bno,
        //         rno: 16,
        //         reply: "댓글 수정..."
        //     },
        //     function (result) {
        //         if (result === "success") {
        //             toastBody.textContent = "댓글이 수정되었습니다."
        //             const toast = new bootstrap.Toast(resultToast)
        //             toast.show()
        //         }
        //     },
        //     function (result) {
        //         //console.log("ERROR: " + result)
        //         toastBody.textContent = "댓글 수정이 실패했습니다. Error: " + result
        //         const toast = new bootstrap.Toast(resultToast)
        //         toast.show()
        //     }
        // )

        //for replyService getList test
        // replyService.remove(
        //     {rno:10},
        //     function (result) {
        //         if(result === "success") {
        //             toastBody.textContent = "댓글이 삭제되었습니다."
        //             const toast = new bootstrap.Toast(resultToast)
        //             toast.show()
        //         }
        //     },
        //     function (result) {
        //         toastBody.textContent = "댓글 삭제가 실패했습니다. Error: " + result
        //         const toast = new bootstrap.Toast(resultToast)
        //         toast.show()
        //     }
        // )
    })
</script>
<script type="text/javascript" src="../resources/assets/js/reply.js"/>
<%@include file="includes/footer.jsp" %>

