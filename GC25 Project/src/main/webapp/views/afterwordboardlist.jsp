<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
	<section class="py-5 container">
        <div class="col-lg-6 col-md-8 mx-auto">
            <nav class="navbar bg-success rounded-pill">
                <div class="mx-auto py-2">
                <span class="navbar-brand mb-0 h1 fs-4">수강 후기</span>
                </div>
            </nav>
        </div>
        <div class="col-lg-5 col-md-8 mx-auto my-3 row">
            <div class="col-lg-6 col-sm-12 text-lg-start">
                <button type="button" class="btn btn-primary">글 작성</button>
            </div>
            <div class="col-lg-6 col-sm-12 text-lg-end">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                      정렬 필터
                    </button>
                    <ul class="dropdown-menu">
                      <li><a class="dropdown-item" href="#">Action</a></li>
                      <li><a class="dropdown-item" href="#">Another action</a></li>
                      <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-lg-5 col-md-8 mx-auto my-3">
            <div class="card my-3">
                <div class="card-body">
                    This is some text within a card body.
                </div>
            </div>
            <div class="card my-3">
                <div class="card-body">
                    This is some text within a card body.
                </div>
            </div>
            <div class="card my-3">
                <div class="card-body">
                    This is some text within a card body.
                </div>
            </div>
            <div class="card my-3">
                <div class="card-body">
                    This is some text within a card body.
                </div>
            </div>
            <div class="card my-3">
                <div class="card-body">
                    This is some text within a card body.
                </div>
            </div>
            <div class="card my-3">
                <div class="card-body">
                    This is some text within a card body.
                </div>
            </div>
        </div>
        <nav class="col-lg-5 col-md-8 mx-auto my-3 " aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>
              <li class="page-item"><a class="page-link" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Next">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </ul>
          </nav>
    </section>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</html>