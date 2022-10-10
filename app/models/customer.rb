class Customer < ApplicationRecord
  belongs_to :store
  validates :store_id, uniqueness: { scope: :email }

  has_many :customer_products
  has_many :products, through: :customer_products
end
