StackExchange.postValidation=function(){function e(e,t,n,i){var r=e.find('input[type="submit"]:visible, button[type="submit"]:visible'),d=r.length&&r.is(":enabled");d&&r.attr("disabled",!0),o(e,i),a(e,t,n,i),c(e),l(e),u(e);var f=function(){1!=t||e.find(E).length?(s(e,i),d&&r.attr("disabled",!1)):setTimeout(f,250)};f()}function t(t,o,a,s,c){e(t,o,s,a);var l,u=function(e){if(e.success)if(c)c(e);else{var n=window.location.href.split("#")[0],i=e.redirectTo.split("#")[0];0==i.indexOf("/")&&(i=window.location.protocol+"//"+window.location.hostname+i),l=!0,window.location=e.redirectTo,n.toLowerCase()==i.toLowerCase()&&window.location.reload(!0)}else e.captchaHtml?StackExchange.nocaptcha.init(e.captchaHtml,u):e.errors?(t.find("input[name=priorAttemptCount]").val(function(e,t){return(+t+1||0).toString()}),h(e.errors,t,o,a,e.warnings)):t.find('input[type="submit"]:visible, button[type="submit"]:visible').parent().showErrorMessage(e.message)};t.submit(function(){var e=t.find('input[type="submit"]:visible, button[type="submit"]:visible');if(t.find("#answer-from-ask").is(":checked"))return!0;var o=t.find(j);if("[Edit removed during grace period]"==$.trim(o.val()))return g(o,["Comment reserved for system use. Please use an appropriate comment."],d()),!1;if(r(),StackExchange.navPrevention&&StackExchange.navPrevention.stop(),e.parent().addSpinner(),StackExchange.helpers.disableSubmitButton(t),StackExchange.options.site.enableNewTagCreationWarning){var s=t.find(E).parent().find("input#tagnames"),c=s.prop("defaultValue");if(s.val()!==c)return $.ajax({"type":"GET","url":"/posts/new-tags-warning","dataType":"json","data":{"tags":s.val()},"success":function(e){if(e.showWarning){var r={"closeOthers":!0,"shown":function(){$(".js-confirm-tag-creation").on("click",function(e){return StackExchange.helpers.closePopups(),i(t,a,l,u),e.preventDefault(),!1})},"dismissing":function(){n(t,l)},"returnElements":$("#tagnames").parent().find("input:visible")};StackExchange.helpers.showModal($(e.html).elementNodesOnly(),r),StackExchange.helpers.bindMovablePopups()}else i(t,a,l,u)}}),!1}return setTimeout(function(){i(t,a,l,u)},0),!1})}function n(e,t){StackExchange.helpers.removeSpinner(),t||StackExchange.helpers.enableSubmitButton(e)}function i(e,t,i,r){$.ajax({"type":"POST","dataType":"json","data":e.serialize(),"url":e.attr("action"),"success":r,"error":function(){var n;switch(t){case"question":n="An error occurred submitting the question.";break;case"answer":n="An error occurred submitting the answer.";break;case"edit":n="An error occurred submitting the edit.";break;case"tags":n="An error occurred submitting the tags.";break;case"post":default:n="An error occurred submitting the post."}e.find('input[type="submit"]:visible, button[type="submit"]:visible').parent().showErrorMessage(n)},"complete":function(){n(e,i)}})}function r(){for(var e=0;e<_.length;e++)clearTimeout(_[e]);_=[]}function o(e,t){var n=e.find(k);n.length&&n.blur(function(){_.push(setTimeout(function(){var i=n.val(),r=$.trim(i);if(p(n),0==r.length)return b(e,n),void 0;var o=n.data("min-length");if(o&&r.length<o)return g(n,[function(e){return 1==e.minLength?"Title must be at least "+e.minLength+" character.":"Title must be at least "+e.minLength+" characters."}({"minLength":o})],d()),void 0;var a=n.data("max-length");return a&&r.length>a?(g(n,[function(e){return 1==e.maxLength?"Title cannot be longer than "+e.maxLength+" character.":"Title cannot be longer than "+e.maxLength+" characters."}({"maxLength":a})],d()),void 0):($.ajax({"type":"POST","url":"/posts/validate-title","data":{"title":i},"success":function(i){i.success?b(e,n):g(n,i.errors.Title,d()),"edit"!=t&&m(e,n,i.warnings.Title)},"error":function(){b(e,n)}}),void 0)},M))})}function a(e,t,n,i){var r=e.find(S);r.length&&r.blur(function(){_.push(setTimeout(function(){var o=r.val(),a=$.trim(o);if(p(r),0==a.length)return b(e,r),void 0;if(5==t){var s=r.data("min-length");return s&&a.length<s?g(r,[function(e){return"Wiki Body must be at least "+e.minLength+" characters. You entered "+e.actual+"."}({"minLength":s,"actual":a.length})],d()):b(e,r),void 0}(1==t||2==t)&&$.ajax({"type":"POST","url":"/posts/validate-body","data":{"body":o,"oldBody":r.prop("defaultValue"),"isQuestion":1==t,"isSuggestedEdit":n},"success":function(t){t.success?b(e,r):g(r,t.errors.Body,d()),"edit"!=i&&m(e,r,t.warnings.Body)},"error":function(){b(e,r)}})},M))})}function s(e,t){var n=e.find(E);if(n.length){var i=n.parent().find("input#tagnames");i.blur(function(){_.push(setTimeout(function(){var r=i.val(),o=$.trim(r);return 0==o.length?(b(e,n),void 0):($.ajax({"type":"POST","url":"/posts/validate-tags","data":{"tags":r,"oldTags":i.prop("defaultValue")},"success":function(i){if(i.success?b(e,n):g(n,i.errors.Tags,d()),"edit"!=t&&(m(e,n,i.warnings.Tags),i.source&&i.source.Tags&&i.source.Tags.length)){var r=$("#post-form input[name='warntags']");r&&StackExchange.using("gps",function(){var e=r.val()||"";$.each(i.source.Tags,function(t,n){n&&!r.data("tag-"+n)&&(r.data("tag-"+n,n),e=e.length?e+" "+n:n,StackExchange.gps.track("tag_warning.show",{"tag":n},!0))}),r.val(e),StackExchange.gps.sendPending()})}},"error":function(){b(e,n)}}),void 0)},M))})}}function c(e){var t=e.find(j);t.length&&t.blur(function(){_.push(setTimeout(function(){var n=t.val(),i=$.trim(n);if(0==i.length)return b(e,t),void 0;var r=t.data("min-length");if(r&&i.length<r)return g(t,[function(e){return 1==e.minLength?"Your edit summary must be at least "+e.minLength+" character.":"Your edit summary must be at least "+e.minLength+" characters."}({"minLength":r})],d()),void 0;var o=t.data("max-length");return o&&i.length>o?(g(t,[function(e){return 1==e.maxLength?"Your edit summary cannot be longer than "+e.maxLength+" character.":"Your edit summary cannot be longer than "+e.maxLength+" characters."}({"maxLength":o})],d()),void 0):(b(e,t),void 0)},M))})}function l(e){var t=e.find(I);t.length&&t.blur(function(){_.push(setTimeout(function(){var n=t.val(),i=$.trim(n);if(0==i.length)return b(e,t),void 0;var r=t.data("min-length");if(r&&i.length<r)return g(t,[function(e){return"Wiki Excerpt must be at least "+e.minLength+" characters; you entered "+e.actual+"."}({"minLength":r,"actual":i.length})],d()),void 0;var o=t.data("max-length");return o&&i.length>o?(g(t,[function(e){return"Wiki Excerpt cannot be longer than "+e.maxLength+" characters; you entered "+e.actual+"."}({"maxLength":o,"actual":i.length})],d()),void 0):(b(e,t),void 0)},M))})}function u(e){var t=e.find(T);t.length&&t.blur(function(){_.push(setTimeout(function(){var n=t.val(),i=$.trim(n);return 0==i.length?(b(e,t),void 0):/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,20}$/i.test(i)?(b(e,t),void 0):(g(t,["This email does not appear to be valid."],f()),void 0)},M))})}function d(){var e=$("#sidebar, .sidebar").first().width()||270,t="lg"===StackExchange.responsive.currentRange();return{"position":{"my":t?"left top":"top center","at":t?"right center":"bottom center"},"css":{"max-width":e,"min-width":e},"closeOthers":!1}}function f(){var e=$("#sidebar, .sidebar").first().width()||270;return{"position":{"my":"left top","at":"right center"},"css":{"min-width":e},"closeOthers":!1}}function p(){Array.prototype.forEach.call(arguments,function(e){var t=StackExchange.stacksValidation.handlerFor(e);t&&t.clear()})}function h(e,t,n,i,r){if(e){var o=function(){var n=0,o=t.find(E),a=t.find(k),s=t.find(S),c=t.find(C);p(a,s,o,c),g(a,e.Title,d())?n++:b(t,a),r&&m(t,a,r.Title),g(s,e.Body,d())?n++:b(t,s),r&&m(t,s,r.Body),g(o,e.Tags,d())?n++:b(t,o),g(c,e.Mentions,d())?n++:b(t,c),r&&m(t,o,r.Tags),g(t.find(j),e.EditComment,d())?n++:b(t,t.find(j)),g(t.find(I),e.Excerpt,d())?n++:b(t,t.find(I)),g(t.find(T),e.Email,f())?n++:b(t,t.find(T)),g(t.find(A),e.Confirmation,d())?n++:b(t,t.find(A));var l=t.find(".general-error"),u=e.General&&e.General.length>0;if(u||n>0){if(!l.length){var h=t.find('input[type="submit"]:visible, button[type="submit"]:visible');h.parent().after('<div class="general-error-container"><div class="general-error"></div><br class="cbt" /></div>'),l=t.find(".general-error")}if(u)g(l,e.General,{"position":"inline","css":{"float":"left","margin-bottom":"10px"},"closeOthers":!1,"dismissable":!1});else{b(t,l);var v;switch(i){case"question":v=function(e){return 1==e.specificErrorCount?"Your question couldn't be submitted. Please see the error above.":"Your question couldn't be submitted. Please see the errors above."}({"specificErrorCount":n});break;case"answer":v=function(e){return 1==e.specificErrorCount?"Your answer couldn't be submitted. Please see the error above.":"Your answer couldn't be submitted. Please see the errors above."}({"specificErrorCount":n});break;case"edit":v=function(e){return 1==e.specificErrorCount?"Your edit couldn't be submitted. Please see the error above.":"Your edit couldn't be submitted. Please see the errors above."}({"specificErrorCount":n});break;case"tags":v=function(e){return 1==e.specificErrorCount?"Your tags couldn't be submitted. Please see the error above.":"Your tags couldn't be submitted. Please see the errors above."}({"specificErrorCount":n});break;case"post":default:v=function(e){return 1==e.specificErrorCount?"Your post couldn't be submitted. Please see the error above.":"Your post couldn't be submitted. Please see the errors above."}({"specificErrorCount":n})}l.text(v)}}else t.find(".general-error-container").remove();var y;x()&&($("#sidebar").animate({"opacity":.4},500),y=setInterval(function(){x()||($("#sidebar").animate({"opacity":1},500),clearInterval(y))},500));var w;t.find(".validation-error, .js-stacks-validation.has-error").each(function(){var e=$(this).offset().top;(!w||w>e)&&(w=e)});var _=function(){for(var e=0;3>e;e++)t.find(".message").animate({"left":"+=5px"},100).animate({"left":"-=5px"},100)};if(w){var M=$(".review-bar").length;w=Math.max(0,w-(M?125:30)),$("html, body").animate({"scrollTop":w},_)}else _()},a=function(){1!=n||t.find(E).length?o():setTimeout(a,250)};a()}}function m(e,t,n){var i=d();if(i.type="warning",!n||0==n.length)return y(e,t),!1;var r=t.data("error-popup"),o=0;return r&&(o=r.height()+5),v(t,n,i,o)}function g(e,t,n){return n.type="error",v(e,t,n)}function v(e,t,n,i){var o,a=n.type,s=StackExchange.stacksValidation.handlerFor(e);if(s)return s.clear(a),(t||[]).forEach(function(e){s.add(a,e)}),!0;if(!(t&&0!=t.length&&e.length&&$("html").has(e).length))return!1;if(o=1==t.length?t[0]:"<ul><li>"+t.join("</li><li>")+"</li></ul>",o&&o.length>0){var c=e.data(a+"-popup");if(c&&c.is(":visible")){var l=e.data(a+"-message");if(l==o)return c.animateOffsetTop&&c.animateOffsetTop(i||0),!0;c.fadeOutAndRemove()}i>0&&(n.position.offsetTop=i);var u=StackExchange.helpers.showMessage(e,o,n);return u.find("a").attr("target","_blank"),u.click(r),e.addClass("validation-"+a).data(a+"-popup",u).data(a+"-message",o),!0}return!1}function y(e,t){w("warning",e,t)}function b(e,t){w("error",e,t)}function w(e,t,n){if(!n||0==n.length)return!1;var i=StackExchange.stacksValidation.handlerFor(n);i&&i.clear(e);var r=n.data(e+"-popup");return r&&r.is(":visible")&&r.fadeOutAndRemove(),n.removeClass("validation-"+e),n.removeData(e+"-popup"),n.removeData(e+"-message"),t.find(".validation-"+e+", .js-stacks-validation.has-"+e).length||t.find(".general-"+e+"-container").remove(),!0}function x(){var e=!1,t=$("#sidebar, .sidebar").first();if(!t.length)return!1;var n=t.offset().left;return $(".message").each(function(){var t=$(this);return t.offset().left+t.outerWidth()>n?(e=!0,!1):void 0}),e}var k="input#title",S="textarea.wmd-input:first",E=".tag-editor:not(.mention-editor)",C=".mention-editor",j="input[id^=edit-comment]",I="textarea#excerpt",T="input#m-address",A="label.ask-confirmation",_=[],M=250;return{"initOnBlur":e,"initOnBlurAndSubmit":t,"showErrorsAfterSubmission":h,"getSidebarPopupOptions":d}}();