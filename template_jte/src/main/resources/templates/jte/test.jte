@import com.jiabin.template.jte.practice.test.Page
@param Page page
<html>
	<head>
		<meta charset="UTF-8">
	    @if(page.getDescription() != null)
	        <meta name="description" content="${page.getDescription()}">
	    @endif
	    <title>${page.getTitle()}</title>
	</head>
	<body>
	    <h1>${page.getTitle()}</h1>
	    <p>欢迎使用JTE模板引擎</p>
	    <hr/>
	    @template.common(page.getEntry(), false)
	</body>
</html>