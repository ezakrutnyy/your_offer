<#import "../macros/common.ftl" as c>
<#import "../macros/offer.ftl" as form>

<@c.page>

    <h5 class="text-primary">Create new offer item</h5>
    <hr/>

     <@form.offer "/offer/create" offer />

    <br/>
</@c.page>