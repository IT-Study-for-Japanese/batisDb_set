<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	인덱스...



	<!-- Insert this script at the bottom of the HTML, but before you use any Firebase services -->
	<script type="module">
    import { initializeApp } from 'https://www.gstatic.com/firebasejs/10.3.0/firebase-app.js'

    // If you enabled Analytics in your project, add the Firebase SDK for Google Analytics
    import { getAnalytics } from 'https://www.gstatic.com/firebasejs/10.3.0/firebase-analytics.js'

    // Add Firebase products that you want to use
    import { getAuth } from 'https://www.gstatic.com/firebasejs/10.3.0/firebase-auth.js'
    import { getFirestore } from 'https://www.gstatic.com/firebasejs/10.3.0/firebase-firestore.js'
  
	const firebaseConfig = {
			apiKey : "YOUR_API_KEY",
			authDomain : "YOUR_AUTH_DOMAIN",
			projectId : "web04-96ce1",
			storageBucket : "YOUR_STORAGE_BUCKET",
			messagingSenderId : "YOUR_MESSAGING_SENDER_ID",
			appId : "YOUR_APP_ID"
	};
	firebase.initializeApp(firebaseConfig);
</script>

	
</body>
</html>