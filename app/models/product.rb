class Product < ApplicationRecord
  validates :name, presence: true
  validates :description, presence: true
  belongs_to :user

  def product_data
    return if data.blank?
    Stripe::Product.construct_from(JSON.parse(data))
  end
end
