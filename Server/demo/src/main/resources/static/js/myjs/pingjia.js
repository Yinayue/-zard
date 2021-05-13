function unenoughpoints() {
    Swal.fire({
        title: 'Not enough points.',
        width: 600,
        padding: '3em',
        showCancelButton: true,
        background: '#fff url(/wimages/trees.png)',
        backdrop: `
    rgba(0,0,123,0.4)
    url("img/cat.gif")
    left top
    no-repeat
  `,confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Buy points!'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                title: 'Buy!',
                imageUrl: "img/charge.jpg",
                showCancelButton: true,
                confirmButtonText: 'Ok!',
                cancelButtonText: 'Something Wrong!',
                reverseButtons: true
            }).then((result2) => {
                if (result2.isConfirmed) {
                    Swal.fire(
                        'Success!',
                        'Check your points.',
                        'success'
                    )
                } else if (
                    result2.dismiss === Swal.DismissReason.cancel
                ) {
                    Swal.fire(
                        'Something Wrong!',
                        'Your problem has been uploaded',
                        'error'
                    )
                }
            });
        }
    });
}
async function t() {
    const { value: password } =  await swal.fire({
        text: 'Please input activation code:',
        input: 'text',
        inputAttributes: {
            id: 'myInput',
        },
        inputPlaceholder:"0000-0000-0000",
        didOpen: function(el) {
            var container = $(el);
            container.find('#myInput').mask('0000-0000-0000');
        }

    });
    if(password) {
        console.log(password+"password");
        if (password == "0000-0000-0000") {
            Swal.fire(
                'Successful!',
                'Check your points.',
                'success'
            )
        } else {
            Swal.fire(
                'Unsuccessful!',
                'Your problem has been uploaded',
                'error'
            )
        }
    }
}