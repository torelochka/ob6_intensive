$(window).keyup(function (e) {
    let target = $('.checkbox-btn input:focus');
    if (e.keyCode === 9 && $(target).length) {
        $(target).parent().addClass('focused');
    }
});

$('.checkbox-btn input').focusout(function () {
    $(this).parent().removeClass('focused');
});

let seanceButton = $('.seance-button')

function closeTable() {
    $("#modal-body-table").html("")
}

seanceButton.on("click", function (elem) {
    let seanceId = elem['target']['attributes'][0]['value']
    let form = $('#form')
    let action = form.attr("action")
    form.attr("action", action + "/seance/" + seanceId)
    function isTaken(seat, data) {
        let disabled = ""
        data.forEach(function (takenSeat) {
            if (takenSeat['takenSeat'] === seat) {
                disabled = "disabled"
            }
        })

        return '<label class="checkbox-btn">' +
            '       <input type="checkbox" name="seat" value="' + seat + '" ' + disabled + '>' +
            '       <span>' + seat.split(";")[1] + '</span> ' +
            '   </label>';
    }

    $.ajax({
        url: "/api/v1/seance/" + seanceId,
        type: "GET",
        dataType: "json",
        success: function (data) {
            let modalBodyTable = $("#modal-body-table")
            modalBodyTable.html("")
            modalBodyTable.append($('<tr>' +
                '                                    <td>1 ряд</td>' +
                '                                    <td>' +
                isTaken("1;1", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("1;2", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("1;3", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("1;4", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("1;5", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("1;6", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("1;7", data) +
                '                                    </td>' +
                '                                </tr>' +
                '                                <tr>' +
                '                                    <td>2 ряд</td>' +
                '                                    <td>' +
                isTaken("2;1", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("2;2", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("2;3", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("2;4", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("2;5", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("2;6", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("2;7", data) +
                '                                    </td>' +
                '                                </tr>' +
                '                                <tr>' +
                '                                    <td>3 ряд</td>' +
                '                                    <td>' +
                isTaken("3;1", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("3;2", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("3;3", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("3;4", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("3;5", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("3;6", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("3;7", data) +
                '                                    </td>' +
                '                                    </td>' +
                '                                </tr>' +
                '                                <tr>' +
                '                                    <td>4 ряд</td>' +
                '                                    <td>' +
                isTaken("4;1", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("4;2", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("4;3", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("4;4", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("4;5", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("4;6", data) +
                '                                    </td>' +
                '                                    <td>' +
                isTaken("4;7", data) +
                '                                    </td>' +
                '                                </tr>'))
        },
        error: function (error) {
            console.log(error)
        }
    })
})