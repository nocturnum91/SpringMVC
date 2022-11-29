<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="includes/header.jsp"%>
<div class="main-wrapper">
  <section class="register-section theme-bg-light py-5">
    <div class="container text-center single-col-max-width">
      <h2 class="heading">DevBlog</h2>
      <div class="single-form-max-width pt-3 mx-auto">
        <form role="form" action="posting" method="post" class="register-form row g-2 g-lg-2 align-items-center">
          <div class="form-group col-12">
            <label class="sr-only" for="title">Title</label>
            <input type="text" id="title" name="title" class="form-control me-md-1 title" placeholder="Title">
          </div>
          <div class="form-group col-12">
            <label class="sr-only" for="content">Content</label>
            <textarea id="content" name="content" class="form-control content" rows="10"></textarea>
          </div>
          <div class="form-group gap-2 d-md-flex justify-content-md-end">
            <button type="submit" class="btn btn-primary">Posting</button>
          </div>
        </form><!--//signup-form-->
      </div><!--//single-form-max-width-->
    </div><!--//container-->
  </section>
  <footer class="footer text-center py-2 theme-bg-dark">
    <!--/* This template is free as long as you keep the footer attribution link. If you'd like to use the template without the attribution link, you can buy the commercial license via our website: themes.3rdwavemedia.com Thank you for your support. :) */-->
    <small class="copyright">Designed with <span class="sr-only">love</span><i class="fas fa-heart" style="color: #fb866a;"></i> by <a href="https://themes.3rdwavemedia.com" target="_blank">Xiaoying Riley</a> for developers</small>
  </footer>
</div><!--//main-wrapper-->
<%@include file="includes/footer.jsp"%>
