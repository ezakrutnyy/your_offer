<#macro login path>
    <form action="${path}" method="post" xmlns="http://www.w3.org/1999/html">

        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">User Name</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="username" name="username" placeholder="User name" required />
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required />
            </div>
        </div>
        <#if path == '/registration'>
            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-6">
                    <input type="email" class="form-control" id="email" name="email" placeholder="some@some.com" required />
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">
            <#if path == '/registration'>Register<#else>Sign in</#if>
        </button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" class="btn btn-primary"  value="Sign Out"/>
    </form>
</#macro>