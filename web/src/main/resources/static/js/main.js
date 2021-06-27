flatpickr.localize(flatpickr.l10ns.ru);
(function ($) {

    /*------------------
		Date Picker
	--------------------*/

    $("#date").flatpickr({
        minDate: new Date(),
        dateFormat: 'Y-m-d',
    })

})(jQuery);