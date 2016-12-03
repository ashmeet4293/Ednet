$(document).ready(function(){
    $('#Delete').click(function(event) {
        event.preventDefault();
        var currentForm = $(this).closest('form');
        
        /** Creating div element for delete confirmation dialog*/
        var dynamicDialog = $('<div id="conformBox">'+
        '<span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;">'+
        '</span>Are you sure to delete the item?</div>');
        
        dynamicDialog.dialog({
                title : "Are you sure?",
               // autoOpen: false,
                modal : true,
                height:200,
                width:300,
                draggable: true,
               
                dialogClass: "foo",
                show: {effect: 'slideDown', duration:1500, direction: 'up' },
                //show: {effect: 'bounce', duration: 1500 },
               // show: {effect: 'fade', duration: 350},
                closeOnEscape: true,
                
        
               buttons : 
                        [{
                                text : "Yes",
                                class : 'confirm_yes_Button' ,
                                click : function() {
                                        $(this).dialog("close");
                                        currentForm.submit();
                                }
                        },
                        {
                                text : "No",
                                class : 'confirm_no_Button' ,
                                click : function() {
                                        $(this).dialog("close");
                                }
                        }]
        });
        
       
        $(".foo .ui-dialog-title").css("font-size", "16px");
        $(".foo .ui-widget-header").css("background", "#088");
        $(".foo .ui-widget-content").css("background-color", "#dff");
        $(".foo .ui-widget-content").css("font-size", "14px");
        $(".ui-corner-all").css("background-color", "#088");
       
        return false;
    });
}); 