<#import "macros/common.ftl" as c>
<#import "macros/login.ftl" as l>

<@c.page>
    <h5 class="text-primary">Registration</h5>
    <hr/>
    <div class="text-danger mb-3">
        ${errorMessage!}
    </div>
    <@l.login "/registration" />
</@c.page>