<#import "../macros/common.ftl" as c>


<@c.page>
    <h5 class="text-primary">User of ${user.username}</h5>
    <hr/>
    <div class="text-danger mb-3">
        ${errorMessage!}
    </div>
    <div class="text-success mb-3">
        ${successMessage!}
    </div>
    <form action="/user/profile" method="post" xmlns="http://www.w3.org/1999/html">

        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="password" name="password" value="${user.password}" placeholder="Password" required />
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" id="email" name="email"  value="${user.email}" required />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</@c.page>