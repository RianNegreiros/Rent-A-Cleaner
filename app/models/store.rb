class Store < ApplicationRecord
  belongs_to :user

  has_many :products, through: :user
  has_many :customers, through: :products

  def self.find_by_request(request)
    where(domain: request.domain)
      .or(where(subdomain: request.subdomain))
      .first
  end
end
