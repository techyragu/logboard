import React from 'react';

class SearchForm extends React.Component {
   constructor(props) {
      super(props);
      this.state = { key: '' }

      this.onKey = (e) => this.setState({ key: e.target.value })

      this.onSearch = () => {
         this.props.onSearch(this.state.key);
         this.setState({ key: '' });
      }
   }
   render() {
      return (
         <table>
            <tbody>
               <tr><td>Search </td><td><input value={this.state.key} onChange={this.onKey} /></td><td><button onClick={this.onSearch}>{this.props.label}</button></td></tr>
            </tbody>
         </table>
      )
   }
}

class SearchResults extends React.Component {
   render() {
      return (
         <table width='100%'>
            <tbody>
               <tr style={{backgroundColor: 'black', color: 'white'}}><th>Id</th><th>TImestamp</th><th>Hostname</th><th>Level</th><th>Message</th></tr>
               {
                  this.props.results.map(log => <tr><td>{log.id}</td><td>{log.timestamp}</td><td>{log.hostname}</td><td>{log.level}</td><td>{log.message}</td></tr>)
               }
            </tbody>
         </table>
      )
   }
}
class Logboard extends React.Component {
   constructor(props) {
      super(props);
      this.state = {
         results: []
      }
      this.onSearchByID = (id) => {
         fetch(`http://localhost:8080/log/${id}`).then(response => {
            response.json().then(log => {
               this.setState({
                  results: [log]
               })
            })
         })
      }
   }
   render() {
      return (
         <div>
            <h1>Cisco Syslog Logboard</h1>
            <hr />
            <hr/>
              <SearchForm onSearch={this.onSearchByID} label="Search by ID"/>
              <hr/>
            <h3>Search Results</h3>
            <SearchResults results={this.state.results}/>
         </div>
      )
   }
}

export default Logboard;
