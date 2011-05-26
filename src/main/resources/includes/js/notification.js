(function($) {
  $(document).ready(function() {
    $.getJSON(AJS.Confluence.getContextPath() + '/rest/badges/1.0/achievements', function(data) {

      $.each(data, function(){
        var achievement = this;
        var options = {
          position: 'center',
          corners: '30px',
          theme: 'jirachivements',
//          sticky: true,
          life: 5000,
          close: function(e,m,o)
          {
            // Change notified attribute to true
            $.ajax({
              url: AJS.Confluence.getContextPath() + '/rest/badges/1.0/achievements/' + achievement.id,
              type: 'PUT',
              data: {
                notified: true
              }
            })
          }
        };

        var imgUrl = AJS.Confluence.getContextPath() + '/download/resources/com.tcs.confluence.plugins.badges/images/achievements/' + this.imageRef + '_small.png';
        var content =
        '<div class="achievement-image">' +
          '<img src="' + imgUrl + '"/>' +
        '</div>' +
        '<div class="achievement-content">' +
          '<h4>' + this.name + '</h4>' +
          '<span class="catchPhrase">' + this.catchPhrase + '</span>' +
        '</div>';

        $.jGrowl(content, options);
      });

            
    });
  });
  
})(jQuery);