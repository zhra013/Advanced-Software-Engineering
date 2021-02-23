<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Post Feed</title>

    <script src="/../js/validation.js"> </script>
    <script src="/../js/jQuery-3.4.1.js"></script>


    <link rel="stylesheet" href="/../css/bulma.css">
    <link rel="stylesheet" href="/../css/app.css">
    <link rel="stylesheet" href="/../css/core.css">

    <link rel="stylesheet" href="/../css/Profile.css">
    <%@include file="bootstrapFiles.jsp" %>
</head>
<body>
    <div class="pageloader"></div>

<h1 style="text-align:left;">Bibliophily Connect</h1>
<%@include file="header.jsp" %>

<div class="view-wrapper">
<c:choose>
    <c:when test="${empty posts}">
        <div class="alert alert-info table-div text-center">
            Currently, no post is available.
        </div>
    </c:when>
    <c:otherwise>
                <!-- Container -->
                        <div id="main-feed" class="container">

                            <!-- Feed page main wrapper -->
                            <div id="activity-feed" class="view-wrap true-dom is-hidden">
                                <div class="columns">
                                    <div class="column is-3 is-hidden-mobile">

                                    </div>
                                    <!-- /Left side column -->

                                    <!-- Middle column -->
                                    <div class="column is-6">
                                        <c:forEach var="post" items="${posts}">
                                        <div id="feed-post-1" class="card is-post">
                                            <!-- Main wrap -->
                                            <div class="content-wrap">
                                                <!-- Post header -->
                                                <div class="card-heading">
                                                    <!-- User meta -->
                                                    <div class="user-block">
                                                        <div class="image">
                                                            <img src="/post/coverPhoto?postId=${post.id}" data-user-popover="1" alt="">
                                                        </div>
                                                        <div class="user-info">
                                                            <b>${post.uploader.userName}</b> posted ${post.postType} <b>${post.title}</b> of Author <b>${post.author}</b> Edition ${post.edition}
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- /Post header -->
                                                   <c:url value="/post/review" var="review">
                                                                                             <c:param name="postId" value="${post.id}"/>
                                                                                       </c:url>
                                                <!-- Post body -->
                                                <div class="card-body">
                                                    <!-- Post body text -->
                                                    <div class="post-text">
                                                        <p>${post.blog}
                                                    </div>
                                                    <!-- Featured image -->
                                                    <div class="post-image">
                                                        <a data-fancybox="post1" data-lightbox-type="comments">
                                                            <img src="/post/coverPhoto?postId=${post.id}" alt="">
                                                        </a>
                                                        <!-- Action buttons -->
                                                        <!-- /partials/pages/feed/buttons/feed-post-actions.html -->
                                                        <div class="like-wrapper">
                                                            <a href="javascript:void(0);" class="like-button">
                                                                <i class="mdi mdi-heart not-liked bouncy"></i>
                                                                <i class="mdi mdi-heart is-liked bouncy"></i>
                                                                <span class="like-overlay"></span>
                                                            </a>
                                                        </div>

                                                        <div class="fab-wrapper is-share">
                                                            <a href="${review}" class="small-fab share-fab modal-trigger">
                                                                <i data-feather="link-2"></i>
                                                            </a>
                                                        </div>

                                                        <div class="fab-wrapper is-comment">
                                                            <a href="javascript:void(0);" class="small-fab">
                                                                <i data-feather="message-circle"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /Post body -->

                                                <!-- Post footer -->
                                                <div class="card-footer">

                                                </div>

                                            </div>

                                        </div>
                                        </c:forEach>
                                        <!-- POST #1 -->

                                    </div>
                                    <!-- /Middle column -->

                                    <!-- Right side column -->
                                    <div class="column is-3">

                                    </div>
                                    <!-- /Right side column -->
                                </div>
                            </div>
                            <!-- /Feed page main wrapper -->
                        </div>
                        <!-- /Container -->
        </div>
    </c:otherwise>
</c:choose>

    </div>


    <script src="https://js.stripe.com/v3/"></script>

    <!-- Core js -->

    <script src="/../js/global.js"></script>
    <script src="/../js/main.js"></script>

    <!-- Page and UI related js -->
    <script src="/../js/feed.js"></script>
    <script src="/../js/stories.js"></script>
    <script src="/../js/chat.js"></script>
    <script src="/../js/inbox.js"></script>
    <script src="/../js/profile.js"></script>
    <script src="/../js/friends.js"></script>
    <script src="/../js/events.js"></script>
    <script src="/../js/explorer.js"></script>
    <script src="/../js/news.js"></script>
    <script src="/../js/questions.js"></script>
    <script src="/../js/videos.js"></script>
    <script src="/../js/shop.js"></script>
    <script src="/../js/settings.js"></script>

    <!-- Components js -->
    <script src="/../js/widgets.js"></script>
    <script src="/../js/autocompletes.js"></script>
    <script src="/../js/modal-uploader.js"></script>
    <script src="/../js/popovers-users.js"></script>
    <script src="/../js/popovers-pages.js"></script>
    <script src="/../js/go-live.js"></script>
    <script src="/../js/lightbox.js"></script>
    <script src="/../js/touch.js"></script>
    <script src="/../js/tour.js"></script>
</body>
</html>