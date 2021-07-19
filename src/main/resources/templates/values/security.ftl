<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    authUserName = user.getUsername()
    isAuthUser = true
    isUserAdmin = user.isAdmin()
    >
<#else>
    <#assign
    isAuthUser = false
    isUserAdmin = false

    >
</#if>