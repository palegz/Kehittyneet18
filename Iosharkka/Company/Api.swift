//
//  Api.swift
//  Company
//
//  Created by Pauli Mänty on 05/11/2018.
//  Copyright © 2018 Pauli Mänty. All rights reserved.
//

import Foundation

struct Api {
    static var companyUrl = "https://home.tamk.fi/~poypek/iosapi17/index.php/"
    static var companyImageUrl = "https://home.tamk.fi/~poypek/iosapi17/"
    
    // this handles api reads
    static func read(getUrl: String, getCompleted:@escaping (Data?, Bool, String) -> Void) {
        let apiUrl = URL(string: self.companyUrl + getUrl)
        URLSession.shared.dataTask(with: apiUrl!) { (data, response, error)  in
            if let httpResponse = response as? HTTPURLResponse {
                if httpResponse.statusCode != 200 {
                    getCompleted(nil, false, String(httpResponse.statusCode))
                } else {
                    getCompleted(data, true, String(httpResponse.statusCode))
                }
            }
            
            }.resume()
    }
    // this handles api create, update and delete
    static func write(method: String, body : [String:Any], postUrl : String, postCompleted : @escaping (Bool, String) -> ()) {
        print (body)
        var request = URLRequest(url: URL(string: self.companyUrl + postUrl)!)
        
        request.httpMethod = method
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("application/json", forHTTPHeaderField: "Accept")
        
        do {
            request.httpBody = try JSONSerialization.data(withJSONObject: body, options: JSONSerialization.WritingOptions.prettyPrinted)
            
        } catch {
            print(error)
        }
        
        URLSession.shared.dataTask(with: request) { (data, response, error) in
            if let httpResponse = response as? HTTPURLResponse {
                if httpResponse.statusCode != 200 {
                    postCompleted(false, String(httpResponse.statusCode))
                } else {
                    postCompleted(true, String(httpResponse.statusCode))
                }
            }
            }.resume()
    }
    

}
