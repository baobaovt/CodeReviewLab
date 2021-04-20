from flask import Flask, render_template, make_response ,redirect , url_for ,request, escape, session, flash, render_template_string
from flask_session import Session
from flask_redis import FlaskRedis
import redis, os ,db
import pickle, html
from base64 import b64decode,b64encode
from binascii import hexlify, unhexlify
from os import environ
from lxml import etree

app = Flask(__name__)
app.secret_key = "s3cr3t"
SESSION_TYPE = 'filesystem'
app.config.from_object(__name__)
Session(app)

@app.route('/')
def index():
    if 'name' in session:
        return 'Logged in as <h1> %s </h1>' % escape(session['name']) + '<span class="psw"><a href="/dashboard">Dashboard</a></span>' + \
        '<br>' +   '<span class="psw"><a href="/logout">LOG OUT</a></span>'
         
        
    else:
        
        output = request.args.get('name')
        output = render_template_string(output)
        if output:
                pass
        else:
                output = "You are not login"
        return """HELLO , WELCOME """+output
 
@app.route('/demo')
def demo():
    return """
    <html>
    <head><title>DEMO PAGE</title></head>
    <body>
        <p><h3>Functions</h3></p>
        <a href="/cookie">Pickle</a><br>
        <a href="/xss">XSS</a><br>
        <a href="/?name=1">SSTI</a><br>
    </body>
    </html>
    """    
    
@app.route('/login',methods=['GET','POST'])
def login():
    if request.method=='GET':
        return render_template('login.html')
        
        
@app.route('/logout', methods =['GET', 'POST'])
def logout():
    session.pop('name', None)
    return redirect('/login')
    
@app.route('/dashboard')    
def dashboard():
    flash("WELCOME, ADMIN")
    return render_template('dashboard.html')
    
@app.route('/about')
def about():
    return render_template('about.html',title="KMA",body="we're talking about Academy of Cryptography techniques")
 
 
@app.route('/response')
def user():
    
    resp= make_response(
        'Test worked!',
        200)
    resp.headers["Content-Type"] = "application/json"
    return resp
 
@app.route('/robots.txt')
def robots():
    return url_for('static', filename='troll.jpg')
  
  
@app.route('/auth', methods =['GET', 'POST'])
def auth():
    if request.method=='POST':
        name = request.form['name']
        if name == '4rth4s' and request.form['psw'] == 'admin':
            res = make_response()
            res.set_cookie('admin', name)
            session['name'] = name
            return redirect('/')               
        else:
            res = make_response('SORRY, YOU ARE NOT ADMIN . PLEASE RE-<span class="psw"><a href="/login">LOGIN</a></span>')
        return res

@app.route("/test")
def home():
    flash("This is a flashed message.")
    return render_template('message.html')

@app.route('/upload')
def upload():
   return render_template('upload.html')
	
@app.route('/uploader', methods = ['GET', 'POST'])
def upload_file():
   if request.method == 'POST':
      f = request.files['file']
      f.save(f.filename)
      return 'file uploaded successfully'

@app.route('/xss')
def xss():
    if request.method == 'POST':
        db.add_comment(request.form['comment'])

    search_query = request.args.get('q')

    comments = db.get_comments(search_query)

    return render_template('xss.html',
                           comments=comments,
                           search_query=search_query)
        

@app.route('/cookie', methods = ['POST', 'GET'])
def cookie():
    cookieValue = None
    value = None
    
    if request.method == 'POST':
        cookieValue = request.form['value']
        value = cookieValue
    elif 'value' in request.cookies:
        cookieValue = pickle.loads(b64decode(request.cookies['value'])) 
    
        
    form = """
    <html>
       <body>Cookie value: """ + str(cookieValue) +"""
          <form action = "/cookie" method = "POST">
             <p><h3>Enter value to be stored in cookie</h3></p>
             <p><input type = 'text' name = 'value'/></p>
             <p><input type = 'submit' value = 'Set Cookie'/></p>
          </form>
       </body>
    </html>
    """
    resp = make_response(form)
    
    if value:
        resp.set_cookie('value', b64encode(pickle.dumps(value)))

    return resp


@app.route('/xml', methods = ['POST', 'GET'])
def xml():
    parsed_xml = None
    if request.method == 'POST':
        xml = request.form['xml']
        parser = etree.XMLParser(no_network=False, dtd_validation=False, load_dtd=True, huge_tree=True)
        #try:
        doc = etree.fromstring(xml.encode(), parser)
        parsed_xml = etree.tostring(doc).decode('utf8')
        #except:
            #pass
    return """
       <html>
          <body>""" + "Result:\n<br>\n" + html.escape(parsed_xml) if parsed_xml else "" + """
             <form action = "/xml" method = "POST">
                <p><h3>Enter xml to parse</h3></p>
                <textarea class="input" name="xml" cols="40" rows="5"></textarea>
                <p><input type = 'submit' value = 'Parse'/></p>
             </form>
          </body>
       </html>
       """
       
       
if __name__ == '__main__':
    app.run()
 