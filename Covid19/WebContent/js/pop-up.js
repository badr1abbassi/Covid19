if(document.getElementById('admin')){
document.getElementById('admin').addEventListener('click',
    function () {
	console.log("hello");
        document.querySelector('.bg-modal').style.display = 'flex';
    });

document.getElementById('close').addEventListener('click',
    function () {
        document.querySelector('.bg-modal').style.display = 'none';
    });
}