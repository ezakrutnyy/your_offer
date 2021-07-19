<#import "macros/common.ftl" as c>
<#import "macros/login.ftl" as l>

<@c.page>
    <h5 class="text-primary">Authentication</h5>
    <hr/>
    <div class="text-danger mb-3">
        ${errorMessage!}
    </div>
    <div class="text-success mb-3">
        ${successMessage!}
    </div>
    <@l.login "/login" />
</@c.page>