<#import "login.ftl" as login>
<#import "../values/security.ftl" as security>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item <#if url?? && url == 'index'> active </#if>">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item <#if url?? && url == 'offers'> active </#if>">
                <a class="nav-link" href="/offer">Offers</a>
            </li>

            <#if security.isUserAdmin>
                <li class="nav-item <#if url?? && url == 'users'> active </#if>">
                    <a class="nav-link" href="/user">Users</a>
                </li>
            </#if>

        </ul>


        <#if security.isAuthUser>
            <div class="navbar-text mr-3">
                <a class="profile" href="/user/profile">
                    <img src="/static/img/profile.png"/>
                    <span >${security.authUserName!}</span>
                </a>
            </div>
            <@login.logout />
        </#if>
        <#if !security.isAuthUser >
            <a href="/login">Auth</a>
            <span class="text-info mx-1">|</span>
            <a href="/registration">Registration</a>
        </#if>
    </div>
</nav>