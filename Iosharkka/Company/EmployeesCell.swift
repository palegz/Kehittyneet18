//
//  EmployeeCellTableViewCell.swift
//  Company
//
//  Created by Pauli Mänty on 05/11/2018.
//  Copyright © 2018 Pauli Mänty. All rights reserved.
//

import UIKit

class EmployeesCell: UITableViewCell {
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var departmentLabel: UILabel!
    @IBOutlet weak var avatarImageView: UIImageView!
    @IBOutlet weak var placeholderImageView: UIImageView!
    
    var imageUrl: URL!
    
    var employee: Employee! {
        didSet {
            nameLabel.text = employee.fname! + " " + employee.lname!
            departmentLabel.text = employee.dname
            print (Api.companyImageUrl + employee.image!)
            if let empImg = employee?.image {
                let imageUrl = URL(string: Api.companyImageUrl + empImg)
                
                if let image = imageUrl!.cachedImage {
                    // Cached: set immediately.
                    avatarImageView.image = image
                    avatarImageView.alpha = 1
                    placeholderImageView.alpha = 0
                } else {
                    // Not cached, so load then fade it in.
                    avatarImageView.alpha = 0
                    placeholderImageView.alpha = 1
                    imageUrl!.fetchImage { image in
                        self.avatarImageView.image = image
                        UIView.animate(withDuration: 0.3, animations: {
                            self.avatarImageView.alpha = 1
                            self.placeholderImageView.alpha = 0
                        })
                    }
                }
            } else {
                // no image
                self.avatarImageView.alpha = 0
                self.placeholderImageView.alpha = 1
            }
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        // Configure the view for the selected state
    }
    
}
