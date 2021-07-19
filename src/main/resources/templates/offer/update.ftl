<#import "../macros/common.ftl" as c>
<#import "../macros/offer.ftl" as form>

<@c.page>

    <h5 class="text-primary">Update offer item</h5>
    <hr/>

    <@form.offer "/offer/update/${offer.id}" offer />

    <br/>
</@c.page>