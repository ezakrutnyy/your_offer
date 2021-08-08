<#macro login path>
    <form action="${path}" method="post" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">

        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">User Name</label>
            <div class="col-sm-6">
                <input type="text" class="form-control  ${(usernameError??)?string('is-invalid', '')}"
                       id="username" name="username" placeholder="User name"
                       value="<#if user??>${user.username}</#if>"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input type="password" class="form-control  ${(passwordError??)?string('is-invalid', '')}"
                       id="password" name="password" placeholder="Password" value="<#if user??>${user.password}</#if>"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if path == '/registration'>

            <div class="form-group row">
                <label for="passwordConfirm" class="col-sm-2 col-form-label">Confirm Password</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control  ${(passwordConfirmError??)?string('is-invalid', '')}"
                           id="passwordConfirm" name="passwordConfirm" placeholder="Confirm Password"
                           value="<#if user??>${user.passwordConfirm}</#if>"/>
                    <#if passwordConfirmError??>
                        <div class="invalid-feedback">
                            ${passwordConfirmError}
                        </div>
                    </#if>
                </div>
            </div>


            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control  ${(emailError??)?string('is-invalid', '')}"
                           id="email" name="email" placeholder="some@some.com"
                           value="<#if user??>${user.email}</#if>"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-6">
                    <div class="g-recaptcha" data-sitekey="6LfejNAbAAAAADVGh_RpWtZF-MF1rA8vo0WlOqAi"></div>
                    <#if captchaError??>
                        <div class="alert alert-danger" role="alert">
                            ${captchaError}
                        </div>
                    </#if>
                </div>
            </div

        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">
            <#if path == '/registration'>Register<#else>Sign in</#if>
        </button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" class="btn btn-primary" value="Sign Out"/>
    </form>
</#macro>